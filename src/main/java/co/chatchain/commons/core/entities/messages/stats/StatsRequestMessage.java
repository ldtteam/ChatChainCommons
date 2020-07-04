package co.chatchain.commons.core.entities.messages.stats;

import co.chatchain.commons.core.interfaces.IMessage;
import com.google.inject.Guice;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class StatsRequestMessage implements IMessage
{
    @NotNull
    private String clientId;

    @NotNull
    private String requestId;

    @NotNull
    private List<String> requestIds = new ArrayList<>();

    @Nullable
    private String statsSection;

    public StatsRequestMessage()
    {
    }

    public StatsRequestMessage(@NotNull final String clientId, @NotNull final List<String> requestIds)
    {
        this.clientId = clientId;
        this.requestIds = requestIds;
    }

    public StatsRequestMessage(@NotNull final String clientId, @NotNull final List<String> requestIds, @Nullable final String statsSection)
    {
        this.clientId = clientId;
        this.requestIds = requestIds;
        this.statsSection = statsSection;
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
    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId(@NotNull final String requestId)
    {
        this.requestId = requestId;
    }

    @NotNull
    public List<String> getRequestIds()
    {
        return requestIds;
    }

    public void setRequestIds(@NotNull final List<String> requestIds)
    {
        this.requestIds = requestIds;
    }

    @Nullable
    public String getStatsSection()
    {
        return statsSection;
    }

    public void setStatsSection(@Nullable final String statsSection)
    {
        this.statsSection = statsSection;
    }
}
