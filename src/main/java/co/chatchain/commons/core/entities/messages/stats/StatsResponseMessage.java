package co.chatchain.commons.core.entities.messages.stats;

import co.chatchain.commons.core.entities.Client;
import co.chatchain.commons.core.entities.StatsObject;
import co.chatchain.commons.core.interfaces.IMessage;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("NullableProblems")
public class StatsResponseMessage implements IMessage
{

    @NotNull
    private String clientId;

    @NotNull
    private Client sendingClient;

    @NotNull
    private String requestId;

    @NotNull
    private StatsObject statsObject;

    public StatsResponseMessage()
    {
    }

    public StatsResponseMessage(@NotNull final String clientId, @NotNull final Client sendingClient, @NotNull final String requestId, @NotNull final StatsObject statsObject)
    {
        this.clientId = clientId;
        this.sendingClient = sendingClient;
        this.requestId = requestId;
        this.statsObject = statsObject;
    }

    @NotNull
    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(@NotNull final String clientId)
    {
        this.clientId = clientId;
    }

    @NotNull
    public Client getSendingClient()
    {
        return sendingClient;
    }

    public void setSendingClient(@NotNull final Client sendingClient)
    {
        this.sendingClient = sendingClient;
    }

    @NotNull
    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId(@NotNull final String requestId)
    {
        this.requestId = requestId;
    }

    @NotNull
    public StatsObject getStatsObject()
    {
        return statsObject;
    }

    public void setStatsObject(@NotNull final StatsObject statsObject)
    {
        this.statsObject = statsObject;
    }
}
