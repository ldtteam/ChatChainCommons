package co.chatchain.commons;

import co.chatchain.commons.messages.interfaces.messages.*;
import com.microsoft.signalr.Action1;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;
import io.reactivex.Single;

public class ChatChainHubConnection
{

    private HubConnection connection;
    private Boolean autoReconnect = true;
    private Thread reconnectionThread;
    private String apiURL;
    private AccessTokenResolver accessTokenResolver;

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
                connect();
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

        connection.start().blockingAwait();


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

    public <T2 extends IGenericMessage> void onGenericMessage(Action1<T2> action, Class<T2> messageClass)
    {
        if (connection != null)
        {
            connection.on("ReceiveGenericMessage", action, messageClass);
        }
    }

    public <T2 extends IGenericMessage> void sendGenericMessage(T2 message)
    {
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.send("SendGenericMessage", message);
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
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.send("SendClientEventMessage", message);
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
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.send("SendUserEventMessage", message);
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
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.send("GetGroups");
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
        if (connection != null && connection.getConnectionState() == HubConnectionState.CONNECTED)
        {
            connection.send("GetClient");
        }
    }

}
