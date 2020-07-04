package co.chatchain.commons.interfaces;

import co.chatchain.commons.core.entities.Client;
import co.chatchain.commons.core.entities.messages.events.ClientEventMessage;
import co.chatchain.commons.core.entities.messages.GenericMessageMessage;
import co.chatchain.commons.core.entities.messages.events.UserEventMessage;
import co.chatchain.commons.core.entities.messages.stats.StatsRequestMessage;
import co.chatchain.commons.core.entities.messages.stats.StatsResponseMessage;
import co.chatchain.commons.core.entities.requests.events.ClientEventRequest;
import co.chatchain.commons.core.entities.requests.GenericMessageRequest;
import co.chatchain.commons.core.entities.requests.events.UserEventRequest;
import co.chatchain.commons.core.entities.requests.stats.StatsRequestRequest;
import co.chatchain.commons.core.entities.requests.stats.StatsResponseRequest;
import com.google.common.cache.Cache;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionState;
import io.reactivex.Single;
import org.jetbrains.annotations.NotNull;
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

    Single<StatsRequestMessage> sendStatsRequestMessage(StatsRequestRequest request);

    Single<StatsResponseMessage> sendStatsResponseMessage(StatsResponseRequest request);

    void sendGetGroups();

    void sendGetClient();

    @Nullable
    Client getClient();

    void setClient(@Nullable Client client);

    void addStatsRequest(@NotNull String requestId, @NotNull String responseLocation);

    @Nullable
    String getStatsRequest(@NotNull String requestId);
}
