package co.chatchain.commons.interfaces;

import co.chatchain.commons.core.entities.Client;
import co.chatchain.commons.core.entities.messages.ClientEventMessage;
import co.chatchain.commons.core.entities.messages.GenericMessageMessage;
import co.chatchain.commons.core.entities.messages.UserEventMessage;
import co.chatchain.commons.core.entities.requests.ClientEventRequest;
import co.chatchain.commons.core.entities.requests.GenericMessageRequest;
import co.chatchain.commons.core.entities.requests.UserEventRequest;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionState;
import io.reactivex.Single;
import org.jetbrains.annotations.Nullable;

public interface IChatChainHubConnection
{
    HubConnection getConnection();

    HubConnectionState getConnectionState();

    void connect();

    void connect(boolean blocking);

    void disconnect();

    void reconnect();

    Single<GenericMessageMessage> sendGenericMessage(GenericMessageRequest request);

    Single<ClientEventMessage> sendClientEventMessage(ClientEventRequest request);

    Single<UserEventMessage> sendUserEventMessage(UserEventRequest request);

    void sendGetGroups();

    void sendGetClient();

    @Nullable
    Client getClient();

    void setClient(@Nullable Client client);
}
