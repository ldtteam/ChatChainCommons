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

    public ChatChainHubConnection(final String apiURL, final String accessToken)
    {
        connection = HubConnectionBuilder.create(apiURL)
                .withAccessTokenProvider(Single.defer(() -> Single.just(accessToken)))
                .build();
    }

    public ChatChainHubConnection(final String apiURL, final AccessTokenResolver accessTokenResolver)
    {
        connection = HubConnectionBuilder.create(apiURL)
                .withAccessTokenProvider(Single.defer(() -> Single.just(accessTokenResolver.getAccessToken())))
                .build();
    }

    public void reconnectionThread()
    {
        while (autoReconnect)
        {
            if (connection.getConnectionState() != HubConnectionState.CONNECTED)
            {
                connect();
            }
        }
    }

    public HubConnection getConnection()
    {
        return connection;
    }

    public HubConnectionState getConnectionState()
    {
        return connection.getConnectionState();
    }

    public void connect()
    {
        connection.start().blockingAwait();
        autoReconnect = true;

        if (!reconnectionThread.isAlive())
        {
            reconnectionThread = new Thread(this::reconnectionThread);
            reconnectionThread.start();
        }
    }

    public void disconnect()
    {
        autoReconnect = false;
        connection.stop().blockingAwait();
    }

    public void reconnect()
    {
        autoReconnect = false;
        connection.stop().blockingAwait();

        connection.start().blockingAwait();
        autoReconnect = true;

        if (!reconnectionThread.isAlive())
        {
            reconnectionThread = new Thread(this::reconnectionThread);
            reconnectionThread.start();
        }
    }

    public <T2 extends IGenericMessage> void onGenericMessage(Action1<T2> action, Class<T2> messageClass)
    {
        connection.on("ReceiveGenericMessage", action, messageClass);
    }

    public <T2 extends IGenericMessage> void sendGenericMessage(T2 message)
    {
        connection.send("SendGenericMessage", message);
    }

    public <T2 extends IClientEventMessage> void onClientEventMessage(Action1<T2> action, Class<T2> messageClass)
    {
        connection.on("ReceiveClientEventMessage", action, messageClass);
    }

    public <T2 extends IClientEventMessage> void sendClientEventMessage(T2 message)
    {
        connection.send("SendClientEventMessage", message);
    }

    public <T2 extends IUserEventMessage> void onUserEventMessage(Action1<T2> action, Class<T2> messageClass)
    {
        connection.on("ReceiveUserEventMessage", action, messageClass);
    }

    public <T2 extends IUserEventMessage> void sendUserEventMessage(T2 message)
    {
        connection.send("SendUserEventMessage", message);
    }

    public <T2 extends IGetGroupsResponse> void onGetGroupsResponse(Action1<T2> action, Class<T2> messageClass)
    {
        connection.on("ReceiveGroups", action, messageClass);
    }

    public void sendGetGroups()
    {
        connection.send("GetGroups");
    }

    public <T2 extends IGetClientResponse> void onGetClientResponse(Action1<T2> action, Class<T2> messageClass)
    {
        connection.on("ReceiveClient", action, messageClass);
    }

    public void sendGetClient()
    {
        connection.send("GetClient");
    }

}
