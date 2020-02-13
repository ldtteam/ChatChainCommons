package co.chatchain.commons.core.entities;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StatsObject
{
    @Nullable
    private List<ClientUser> onlineUsers;

    @Nullable
    private String performance;

    @Nullable
    private String performanceName;

    public StatsObject()
    {
    }

    public StatsObject(@Nullable final List<ClientUser> onlineUsers, @Nullable final String performance, @Nullable final String performanceName)
    {
        this.onlineUsers = onlineUsers;
        this.performance = performance;
        this.performanceName = performanceName;
    }

    @Nullable
    public List<ClientUser> getOnlineUsers()
    {
        return onlineUsers;
    }

    public void setOnlineUsers(@Nullable final List<ClientUser> onlineUsers)
    {
        this.onlineUsers = onlineUsers;
    }

    @Nullable
    public String getPerformance()
    {
        return performance;
    }

    public void setPerformance(@Nullable final String performance)
    {
        this.performance = performance;
    }

    @Nullable
    public String getPerformanceName()
    {
        return performanceName;
    }

    public void setPerformanceName(@Nullable final String performanceName)
    {
        this.performanceName = performanceName;
    }
}
