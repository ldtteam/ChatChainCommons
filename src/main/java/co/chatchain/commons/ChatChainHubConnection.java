package co.chatchain.commons;

import co.chatchain.commons.messages.interfaces.messages.*;
import com.microsoft.signalr.Action1;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;

public class ChatChainHubConnection
{
    private HubConnection connection;
    private Boolean autoReconnect = true;
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

    public void reconnectionThread()
    {
        while (autoReconnect)
        {
            if (connection == null || connection.getConnectionState() != HubConnectionState.CONNECTED)
            {
                connect(false);
                break;
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

        autoReconnect = true;

        if (reconnectionThread == null || !reconnectionThread.isAlive())
        {
            reconnectionThread = new Thread(this::reconnectionThread);
            reconnectionThread.start();
        }
    }

    public void disconnect()
    {
        autoReconnect = false;
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

    public <T2 extends IGenericMessage> void onGenericMessage(Action1<T2> action, Class<T2> messageClass)
    {
        if (connection != null)
        {
            connection.on("ReceiveGenericMessage", action, messageClass);
        }
    }

    public <T2 extends IGenericMessage> void sendGenericMessage(T2 message)
    {
        sendGenericMessage(message, true);
    }

    public <T2 extends IGenericMessage> void sendGenericMessage(T2 message, final boolean sendWhenConnected)
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.send("SendGenericMessage", message);
        }
        else if (sendWhenConnected)
        {
            onConnectActionsOnce.add(connection -> connection.sendGenericMessage(message));
        }
    }

    public <T2 extends IClientEventMessage> void onClientEventMessage(Action1<T2> action, Class<T2> messageClass)
    {
        if (connection != null)
        {
            connection.on("ReceiveClientEventMessage", action, messageClass);
        }
    }

    public <T2 extends IClientEventMessage> void sendClientEventMessage(T2 message)
    {
        sendClientEventMessage(message, true);
    }

    public <T2 extends IClientEventMessage> void sendClientEventMessage(T2 message, final boolean sendWhenConnected)
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.send("SendClientEventMessage", message);
        }
        else if (sendWhenConnected)
        {
            onConnectActionsOnce.add(connection -> connection.sendClientEventMessage(message));
        }
    }

    public <T2 extends IUserEventMessage> void onUserEventMessage(Action1<T2> action, Class<T2> messageClass)
    {
        if (connection != null)
        {
            connection.on("ReceiveUserEventMessage", action, messageClass);
        }
    }

    public <T2 extends IUserEventMessage> void sendUserEventMessage(T2 message)
    {
        sendUserEventMessage(message, true);
    }

    public <T2 extends IUserEventMessage> void sendUserEventMessage(T2 message, final boolean sendWhenConnected)
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.send("SendUserEventMessage", message);
        }
        else if (sendWhenConnected)
        {
            onConnectActionsOnce.add(connection -> connection.sendUserEventMessage(message));
        }
    }

    public <T2 extends IGetGroupsResponse> void onGetGroupsResponse(Action1<T2> action, Class<T2> messageClass)
    {
        if (connection != null)
        {
            connection.on("ReceiveGroups", action, messageClass);
        }
    }

    public void sendGetGroups()
    {
        sendGetGroups(true);
    }

    public void sendGetGroups(final boolean sendWhenConnected)
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.send("GetGroups");
        }
        else if (sendWhenConnected)
        {
            onConnectActionsOnce.add(ChatChainHubConnection::sendGetGroups);
        }
    }

    public <T2 extends IGetClientResponse> void onGetClientResponse(Action1<T2> action, Class<T2> messageClass)
    {
        if (connection != null)
        {
            connection.on("ReceiveClient", action, messageClass);
        }
    }

    public void sendGetClient()
    {
        sendGetClient(true);
    }

    public void sendGetClient(final boolean sendWhenConnected)
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.send("GetClient");
        }
        else if (sendWhenConnected)
        {
            onConnectActionsOnce.add(ChatChainHubConnection::sendGetClient);
        }
    }

}
