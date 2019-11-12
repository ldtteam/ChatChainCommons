package co.chatchain.commons;

import co.chatchain.commons.core.entities.Client;
import co.chatchain.commons.core.entities.messages.*;
import co.chatchain.commons.core.entities.requests.ClientEventRequest;
import co.chatchain.commons.core.entities.requests.GenericMessageRequest;
import co.chatchain.commons.core.entities.requests.UserEventRequest;
import co.chatchain.commons.core.interfaces.cases.*;
import co.chatchain.commons.interfaces.IAccessTokenResolver;
import co.chatchain.commons.interfaces.IChatChainHubConnection;
import co.chatchain.commons.interfaces.IConnectionConfig;
import co.chatchain.commons.interfaces.ILogger;
import co.chatchain.commons.queue.MessageConsumer;
import co.chatchain.commons.queue.MessageSendRequest;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;
import io.reactivex.Single;
import io.reactivex.subjects.SingleSubject;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.concurrent.LinkedBlockingQueue;
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
    private final IReceiveClientCase receiveClientCase;
    private final IReceiveClientEventCase receiveClientEventCase;
    private final IReceiveGenericMessageCase receiveGenericMessageCase;
    private final IReceiveGroupsCase receiveGroupsCase;
    private final IReceiveUserEventCase receiveUserEventCase;

    @Inject
    public ChatChainHubConnection(final IConnectionConfig connectionConfig,
                                  final IAccessTokenResolver accessTokenResolver,
                                  final ILogger logger,
                                  final IReceiveClientCase receiveClientCase,
                                  final IReceiveClientEventCase receiveClientEventCase,
                                  final IReceiveGenericMessageCase receiveGenericMessageCase,
                                  final IReceiveGroupsCase receiveGroupsCase,
                                  final IReceiveUserEventCase receiveUserEventCase)
    {
        this.connectionConfig = connectionConfig;
        this.accessTokenResolver = accessTokenResolver;
        this.logger = logger;
        this.receiveClientCase = receiveClientCase;
        this.receiveClientEventCase = receiveClientEventCase;
        this.receiveGenericMessageCase = receiveGenericMessageCase;
        this.receiveGroupsCase = receiveGroupsCase;
        this.receiveUserEventCase = receiveUserEventCase;
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
            } catch (InterruptedException e)
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
        } catch (Exception e)
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
            sendGetClient();
            connection.on("ReceiveClientEventMessage", receiveClientEventCase::handle, ClientEventMessage.class);
            connection.on("ReceiveGenericMessage", receiveGenericMessageCase::handle, GenericMessageMessage.class);
            sendGetGroups();
            connection.on("ReceiveUserEventMessage", receiveUserEventCase::handle, UserEventMessage.class);
        }

        if (reconnectionThread != null)
        {
            autoReconnect.set(false);
            while (reconnectionThread.isAlive())
            {
                try
                {
                    Thread.sleep(100);
                } catch (InterruptedException e)
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

    @Override
    public Single<GenericMessageMessage> sendGenericMessage(final GenericMessageRequest request)
    {
        SingleSubject<GenericMessageMessage> singleSubject = SingleSubject.create();
        try
        {
            MessageSendRequest<GenericMessageMessage> sendRequest = new MessageSendRequest<>(conn -> singleSubject.onSuccess(conn.invoke(GenericMessageMessage.class, "SendGenericMessage", request).blockingGet()));
            messageQueue.put(sendRequest);
        }
        catch (final InterruptedException e)
        {
            singleSubject.onError(e);
            logger.error("Error adding Generic Message to Queue", e);
        }
        return singleSubject;
    }

    @Override
    public Single<ClientEventMessage> sendClientEventMessage(final ClientEventRequest request)
    {
        SingleSubject<ClientEventMessage> singleSubject = SingleSubject.create();
        try
        {
            MessageSendRequest<ClientEventMessage> sendRequest = new MessageSendRequest<>(conn -> singleSubject.onSuccess(conn.invoke(ClientEventMessage.class, "SendClientEventMessage", request).blockingGet()));
            messageQueue.put(sendRequest);
        }
        catch (final InterruptedException e)
        {
            singleSubject.onError(e);
            logger.error("Error adding Client Event to Queue", e);
        }
        return singleSubject;
    }

    @Override
    public Single<UserEventMessage> sendUserEventMessage(UserEventRequest request)
    {
        SingleSubject<UserEventMessage> singleSubject = SingleSubject.create();
        try
        {
            MessageSendRequest<UserEventMessage> sendRequest = new MessageSendRequest<>(conn -> singleSubject.onSuccess(conn.invoke(UserEventMessage.class, "SendUserEventMessage", request).blockingGet()));
            messageQueue.put(sendRequest);
        }
        catch (final InterruptedException e)
        {
            singleSubject.onError(e);
            logger.error("Error adding User Event to Queue", e);
        }
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
}
