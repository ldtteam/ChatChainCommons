package co.chatchain.commons;

import co.chatchain.commons.core.entities.Client;
import co.chatchain.commons.core.entities.messages.*;
import co.chatchain.commons.core.entities.messages.events.ClientEventMessage;
import co.chatchain.commons.core.entities.messages.events.UserEventMessage;
import co.chatchain.commons.core.entities.messages.stats.StatsRequestMessage;
import co.chatchain.commons.core.entities.messages.stats.StatsResponseMessage;
import co.chatchain.commons.core.entities.requests.events.ClientEventRequest;
import co.chatchain.commons.core.entities.requests.GenericMessageRequest;
import co.chatchain.commons.core.entities.requests.events.UserEventRequest;
import co.chatchain.commons.core.entities.requests.stats.StatsRequestRequest;
import co.chatchain.commons.core.entities.requests.stats.StatsResponseRequest;
import co.chatchain.commons.core.interfaces.cases.*;
import co.chatchain.commons.core.interfaces.cases.events.IReceiveClientEventCase;
import co.chatchain.commons.core.interfaces.cases.events.IReceiveUserEventCase;
import co.chatchain.commons.core.interfaces.cases.stats.IReceiveStatsRequestCase;
import co.chatchain.commons.core.interfaces.cases.stats.IReceiveStatsResponseCase;
import co.chatchain.commons.interfaces.IAccessTokenResolver;
import co.chatchain.commons.interfaces.IChatChainHubConnection;
import co.chatchain.commons.interfaces.IConnectionConfig;
import co.chatchain.commons.interfaces.ILogger;
import co.chatchain.commons.queue.MessageConsumer;
import co.chatchain.commons.queue.MessageSendRequest;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;
import io.reactivex.Single;
import io.reactivex.subjects.SingleSubject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings({"UnusedReturnValue"})
public class ChatChainHubConnection implements IChatChainHubConnection
{
    /**
     * SignalR HubConnection instance
     */
    private HubConnection connection;

    /**
     * ConnectionConfig to get HubUrl from.
     */
    private final IConnectionConfig connectionConfig;

    /**
     * AccessTokenResolver to receive accessToken for hub connection.
     */
    private final IAccessTokenResolver accessTokenResolver;

    /**
     * Thread used to allow for connection to Auto Reconnect.
     */
    private Thread reconnectionThread;

    /**
     * AtomicBoolean flag to stop the reconnection thread as needed.
     */
    private final AtomicBoolean autoReconnect = new AtomicBoolean(true);

    /**
     * Linked Queue for the MessageQueue for storing and then sending messages to the Hub.
     */
    private final LinkedBlockingQueue<MessageSendRequest> messageQueue = new LinkedBlockingQueue<>();

    /**
     * Cache for the Stats Requests and what a Client may bind them to
     */
    private final Cache<String, String> statsRequestCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    /**
     * Message Consumer thread for sending messages from queue to Hub.
     */
    private Thread messageConsumerThread;

    /**
     * This hubConnection's client.
     */
    @Nullable
    private Client client = null;

    /**
     * Logger instance given by DI.
     */
    private final ILogger logger;

    /**
     * Use cases given through DI for how to handle hub messages received.
     */
    private final IReceiveGenericMessageCase receiveGenericMessageCase;

    private final IReceiveClientEventCase receiveClientEventCase;
    private final IReceiveUserEventCase receiveUserEventCase;

    private final IReceiveStatsRequestCase receiveStatsRequestCase;
    private final IReceiveStatsResponseCase receiveStatsResponseCase;

    private final IReceiveClientCase receiveClientCase;
    private final IReceiveGroupsCase receiveGroupsCase;

    @Inject
    public ChatChainHubConnection(final IConnectionConfig connectionConfig, final IAccessTokenResolver accessTokenResolver, final ILogger logger, final IReceiveGenericMessageCase receiveGenericMessageCase, final IReceiveClientEventCase receiveClientEventCase, final IReceiveUserEventCase receiveUserEventCase, final IReceiveStatsRequestCase receiveStatsRequestCase, final IReceiveStatsResponseCase receiveStatsResponseCase, final IReceiveClientCase receiveClientCase, final IReceiveGroupsCase receiveGroupsCase)
    {
        this.connectionConfig = connectionConfig;
        this.accessTokenResolver = accessTokenResolver;
        this.logger = logger;
        this.receiveGenericMessageCase = receiveGenericMessageCase;
        this.receiveClientEventCase = receiveClientEventCase;
        this.receiveUserEventCase = receiveUserEventCase;
        this.receiveStatsRequestCase = receiveStatsRequestCase;
        this.receiveStatsResponseCase = receiveStatsResponseCase;
        this.receiveClientCase = receiveClientCase;
        this.receiveGroupsCase = receiveGroupsCase;
    }

    private void reconnectionThread()
    {
        while (autoReconnect.get())
        {
            if (connection == null || connection.getConnectionState() != HubConnectionState.CONNECTED)
            {
                connect(false);
            }
            try
            {
                Thread.sleep(30000);
            } catch (final InterruptedException e)
            {
                System.out.println("Problem creating auto reconnection thread: ");
                e.printStackTrace();
            }
        }
    }

    @Override
    public HubConnection getConnection()
    {
        return connection;
    }

    @Override
    public HubConnectionState getConnectionState()
    {
        if (connection != null)
        {
            return connection.getConnectionState();
        }
        return HubConnectionState.DISCONNECTED;
    }

    @Override
    public void connect()
    {
        connect(true);
    }

    @Override
    public void connect(final boolean blocking)
    {

        final String accessToken;
        try
        {
            accessToken = accessTokenResolver.getAccessToken();
        } catch (final Exception e)
        {
            System.out.println("Problem with getting access token, please check server availability");
            return;
        }

        connection = HubConnectionBuilder.create(connectionConfig.getHubUrl().toString())
                .withAccessTokenProvider(Single.defer(() -> Single.just(accessToken)))
                .build();

        if (blocking)
        {
            waitForConnectedThread();
        }
        else
        {
            new Thread(this::waitForConnectedThread).start();
        }
    }

    private void waitForConnectedThread()
    {
        connection.start().blockingAwait();

        if (messageConsumerThread == null || !messageConsumerThread.isAlive())
        {
            messageConsumerThread = new Thread(new MessageConsumer(messageQueue, this));
            messageConsumerThread.start();
        }

        if (connection != null && connection.getConnectionState().equals(HubConnectionState.CONNECTED))
        {
            connection.on("ReceiveGenericMessage", receiveGenericMessageCase::handle, GenericMessageMessage.class);

            connection.on("ReceiveClientEventMessage", receiveClientEventCase::handle, ClientEventMessage.class);
            connection.on("ReceiveUserEventMessage", receiveUserEventCase::handle, UserEventMessage.class);

            connection.on("ReceiveStatsRequest", receiveStatsRequestCase::handle, StatsRequestMessage.class);
            connection.on("ReceiveStatsResponse", receiveStatsResponseCase::handle, StatsResponseMessage.class);

            sendGetClient();
            sendGetGroups();

            sendClientEventMessage(new ClientEventRequest("START", null));
        }

        if (reconnectionThread != null)
        {
            autoReconnect.set(false);
            while (reconnectionThread.isAlive())
            {
                try
                {
                    Thread.sleep(100);
                } catch (final InterruptedException e)
                {
                    System.out.println("Problem creating auto reconnection thread: ");
                    e.printStackTrace();
                }
            }
        }
        autoReconnect.set(true);
        reconnectionThread = new Thread(this::reconnectionThread);
        reconnectionThread.start();
    }

    @Override
    public void disconnect()
    {
        autoReconnect.set(false);
        connection.stop().blockingAwait();
        connection = null;
    }

    @Override
    public void reconnect()
    {
        disconnect();

        connect();
    }

    private void addSendRequest(final MessageSendRequest sendRequest, final SingleSubject singleSubject)
    {
        if (getConnectionState().equals(HubConnectionState.CONNECTED) && (messageConsumerThread == null || !messageConsumerThread.isAlive()))
        {
            messageConsumerThread = new Thread(new MessageConsumer(messageQueue, this));
            messageConsumerThread.start();
        }

        try
        {
            messageQueue.put(sendRequest);
        }
        catch (final InterruptedException e)
        {
            singleSubject.onError(e);
            logger.error("Error adding Message to Queue", e);
        }
    }

    @Override
    public Single<GenericMessageMessage> sendGenericMessage(final GenericMessageRequest request)
    {
        final SingleSubject<GenericMessageMessage> singleSubject = SingleSubject.create();
        final MessageSendRequest<GenericMessageMessage> sendRequest = new MessageSendRequest<>(conn -> singleSubject.onSuccess(conn.invoke(GenericMessageMessage.class, "SendGenericMessage", request).blockingGet()));

        addSendRequest(sendRequest, singleSubject);
        return singleSubject;
    }

    @Override
    public Single<ClientEventMessage> sendClientEventMessage(final ClientEventRequest request)
    {
        final SingleSubject<ClientEventMessage> singleSubject = SingleSubject.create();
        final MessageSendRequest<ClientEventMessage> sendRequest = new MessageSendRequest<>(conn -> singleSubject.onSuccess(conn.invoke(ClientEventMessage.class, "SendClientEventMessage", request).blockingGet()));

        addSendRequest(sendRequest, singleSubject);
        return singleSubject;
    }

    @Override
    public Single<UserEventMessage> sendUserEventMessage(final UserEventRequest request)
    {
        final SingleSubject<UserEventMessage> singleSubject = SingleSubject.create();
        final MessageSendRequest<UserEventMessage> sendRequest = new MessageSendRequest<>(conn -> singleSubject.onSuccess(conn.invoke(UserEventMessage.class, "SendUserEventMessage", request).blockingGet()));

        addSendRequest(sendRequest, singleSubject);
        return singleSubject;
    }

    @Override
    public Single<StatsRequestMessage> sendStatsRequestMessage(final StatsRequestRequest request)
    {
        final SingleSubject<StatsRequestMessage> singleSubject = SingleSubject.create();
        final MessageSendRequest<StatsRequestMessage> sendRequest = new MessageSendRequest<>(conn -> singleSubject.onSuccess(conn.invoke(StatsRequestMessage.class, "SendStatsRequest", request).blockingGet()));
    
        addSendRequest(sendRequest, singleSubject);
        return singleSubject;
    }

    @Override
    public Single<StatsResponseMessage> sendStatsResponseMessage(final StatsResponseRequest request)
    {
        final SingleSubject<StatsResponseMessage> singleSubject = SingleSubject.create();
        final MessageSendRequest<StatsResponseMessage> sendRequest = new MessageSendRequest<>(conn -> singleSubject.onSuccess(conn.invoke(StatsResponseMessage.class, "SendStatsResponse", request).blockingGet()));

        addSendRequest(sendRequest, singleSubject);
        return singleSubject;
    }

    @Override
    public void sendGetGroups()
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            receiveGroupsCase.handle(connection.invoke(GetGroupsMessage.class, "GetGroups").blockingGet());
        }
    }

    @Override
    public void sendGetClient()
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            receiveClientCase.handle(connection.invoke(GetClientMessage.class, "GetClient").blockingGet());
        }
    }

    @Override
    @Nullable
    public Client getClient()
    {
        return client;
    }

    @Override
    public void setClient(@Nullable final Client client)
    {
        this.client = client;
    }

    @Override
    public void addStatsRequest(@NotNull final String requestId, @NotNull final String responseLocation)
    {
        this.statsRequestCache.put(requestId, responseLocation);
    }

    @Nullable
    @Override
    public String getStatsRequest(@NotNull final String requestId)
    {
        return this.statsRequestCache.getIfPresent(requestId);
    }
}
