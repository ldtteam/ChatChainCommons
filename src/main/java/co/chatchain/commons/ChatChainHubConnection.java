package co.chatchain.commons;

import co.chatchain.commons.messages.abstracts.messages.*;
import com.microsoft.signalr.Action1;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;
import io.reactivex.Single;

public class ChatChainHubConnection
{

    private HubConnection connection;

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
    }

    public void disconnect()
    {
        connection.stop().blockingAwait();
    }

    public void reconnect()
    {
        connection.stop().blockingAwait();

        connection.start().blockingAwait();
    }

    public <T1 extends AbstractGenericMessage> void onGenericMessage(Action1<T1> action, Class<T1> messageClass)
    {
        connection.on("ReceiveGenericMessage", action, messageClass);
    }

    public <T1 extends AbstractGenericMessage> void sendGenericMessage(T1 message)
    {
        connection.send("SendGenericMessage", message);
    }

    public <T1 extends AbstractClientEventMessage> void onClientEventMessage(Action1<T1> action, Class<T1> messageClass)
    {
        connection.on("ReceiveClientEventMessage", action, messageClass);
    }

    public <T1 extends AbstractClientEventMessage> void sendClientEventMessage(T1 message)
    {
        connection.send("SendClientEventMessage", message);
    }

    public <T1 extends AbstractUserEventMessage> void onUserEventMessage(Action1<T1> action, Class<T1> messageClass)
    {
        connection.on("ReceiveUserEventMessage", action, messageClass);
    }

    public <T1 extends AbstractUserEventMessage> void sendUserEventMessage(T1 message)
    {
        connection.send("SendUserEventMessage", message);
    }

    public <T1 extends AbstractGetGroupsResponse> void onGetGroupsResponse(Action1<T1> action, Class<T1> messageClass)
    {
        connection.on("ReceiveGroups", action, messageClass);
    }

    public void sendGetGroups()
    {
        connection.send("GetGroups");
    }

    public <T1 extends AbstractGetClientResponse> void onGetClientResponse(Action1<T1> action, Class<T1> messageClass)
    {
        connection.on("ReceiveClient", action, messageClass);
    }

    public void sendGetClient()
    {
        connection.send("GetClient");
    }

}
