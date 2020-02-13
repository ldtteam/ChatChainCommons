package co.chatchain.commons.core.entities.requests.stats;

import org.jetbrains.annotations.Nullable;

public class StatsRequestRequest
{
    @Nullable
    private String requestedClient;

    @Nullable
    private String requestedGroup;

    @Nullable
    private String statsSection;

    public StatsRequestRequest()
    {
    }

    public StatsRequestRequest(@Nullable final String requestedClient, @Nullable final String requestedGroup)
    {
        this.requestedClient = requestedClient;
        this.requestedGroup = requestedGroup;
    }

    public StatsRequestRequest(@Nullable final String requestedClient, @Nullable final String requestedGroup, @Nullable final String statsSection)
    {
        this.requestedClient = requestedClient;
        this.requestedGroup = requestedGroup;
        this.statsSection = statsSection;
    }

    @Nullable
    public String getRequestedClient()
    {
        return requestedClient;
    }

    public void setRequestedClient(@Nullable final String requestedClient)
    {
        this.requestedClient = requestedClient;
    }

    @Nullable
    public String getRequestedGroup()
    {
        return requestedGroup;
    }

    public void setRequestedGroup(@Nullable final String requestedGroup)
    {
        this.requestedGroup = requestedGroup;
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
