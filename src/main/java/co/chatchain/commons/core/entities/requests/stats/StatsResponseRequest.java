package co.chatchain.commons.core.entities.requests.stats;

import co.chatchain.commons.core.entities.StatsObject;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("NullableProblems")
public class StatsResponseRequest
{
    @NotNull
    private String requestId;

    @NotNull
    private StatsObject statsObject;

    public StatsResponseRequest()
    {
    }

    public StatsResponseRequest(@NotNull final String requestId, @NotNull final StatsObject statsObject)
    {
        this.requestId = requestId;
        this.statsObject = statsObject;
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
