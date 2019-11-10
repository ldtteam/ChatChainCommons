package co.chatchain.commons;

import co.chatchain.commons.core.entites.messages.*;
import co.chatchain.commons.core.entites.requests.ClientEventRequest;
import co.chatchain.commons.core.entites.requests.GenericMessageRequest;
import co.chatchain.commons.core.entites.requests.UserEventRequest;
import com.microsoft.signalr.Action1;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChatChainHubConnection
{
    private HubConnection connection;
    private AtomicBoolean autoReconnect = new AtomicBoolean(true);
    private Thread reconnectionThread;
    private String apiURL;
    private AccessTokenResolver accessTokenResolver;
    private List<Action1<ChatChainHubConnection>> onConnectActions = new ArrayList<>();
    private List<Action1<ChatChainHubConnection>> onConnectActionsOnce = new ArrayList<>();

    public ChatChainHubConnection(final String apiURL, final AccessTokenResolver accessTokenResolver)
    {
        this.apiURL = apiURL;
        this.accessTokenResolver = accessTokenResolver;
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
            }
            catch (InterruptedException e)
            {
                System.out.println("Problem creating auto reconnection thread: ");
                e.printStackTrace();
            }
        }
    }

    public HubConnection getConnection()
    {
        return connection;
    }

    public HubConnectionState getConnectionState()
    {
        if (connection != null)
        {
            return connection.getConnectionState();
        }
        return HubConnectionState.DISCONNECTED;
    }

    public void connect()
    {
        connect(true);
    }

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

        connection = HubConnectionBuilder.create(apiURL)
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

        if (connection != null && connection.getConnectionState().equals(HubConnectionState.CONNECTED))
        {
            for (Action1<ChatChainHubConnection> action : onConnectActions)
            {
                action.invoke(this);
            }

            for (Action1<ChatChainHubConnection> action : onConnectActionsOnce)
            {
                action.invoke(this);
            }
            onConnectActionsOnce = new ArrayList<>();
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

    public void disconnect()
    {
        autoReconnect.set(false);
        connection.stop().blockingAwait();
        connection = null;
    }

    public void reconnect()
    {
        disconnect();

        connect();
    }

    /**
     * This method is for registering things you'd like to happen every time the client connects. good place to put message listeners!
     * @param callback the lambda you want executed
     */
    public void onConnection(Action1<ChatChainHubConnection> callback)
    {
        onConnectActions.add(callback);

        if (connection != null)
        {
            callback.invoke(this);
        }
    }

    public void onGenericMessage(Action1<GenericMessageMessage> action)
    {
        if (connection != null)
        {
            connection.on("ReceiveGenericMessage", action, GenericMessageMessage.class);
        }
    }

    public Single<GenericMessageMessage> sendGenericMessage(GenericMessageRequest request)
    {
        return sendGenericMessage(request, true);
    }

    public Single<GenericMessageMessage> sendGenericMessage(GenericMessageRequest request, final boolean sendWhenConnected)
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.invoke(GenericMessageMessage.class, "SendGenericMessage", request);
        }
        else if (sendWhenConnected)
        {
            onConnectActionsOnce.add(connection -> connection.sendGenericMessage(request));
        }
        return null;
    }

    public void onClientEventMessage(Action1<ClientEventMessage> action)
    {
        if (connection != null)
        {
            connection.on("ReceiveClientEventMessage", action, ClientEventMessage.class);
        }
    }

    public Single<ClientEventMessage> sendClientEventMessage(ClientEventRequest request)
    {
        return sendClientEventMessage(request, true);
    }

    public Single<ClientEventMessage> sendClientEventMessage(ClientEventRequest request, final boolean sendWhenConnected)
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            return connection.invoke(ClientEventMessage.class, "SendClientEventMessage", request);
        }
        else if (sendWhenConnected)
        {
            onConnectActionsOnce.add(connection -> connection.sendClientEventMessage(request));
        }
        return null;
    }

    public void onUserEventMessage(Action1<UserEventMessage> action)
    {
        if (connection != null)
        {
            connection.on("ReceiveUserEventMessage", action, UserEventMessage.class);
        }
    }

    public Single<UserEventMessage> sendUserEventMessage(UserEventRequest request)
    {
        return sendUserEventMessage(request, true);
    }

    public Single<UserEventMessage> sendUserEventMessage(UserEventRequest request, final boolean sendWhenConnected)
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            return connection.invoke(UserEventMessage.class, "SendUserEventMessage", request);
        }
        else if (sendWhenConnected)
        {
            onConnectActionsOnce.add(connection -> connection.sendUserEventMessage(request));
        }
        return null;
    }

    public Single<GetGroupsMessage> sendGetGroups()
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            return connection.invoke(GetGroupsMessage.class, "GetGroups");
        }
        return null;
    }

    public Single<GetClientMessage> sendGetClient()
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            return connection.invoke(GetClientMessage.class, "GetClient");
        }
        return null;
    }

}
