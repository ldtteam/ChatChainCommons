package co.chatchain.commons.objects.messages;

import co.chatchain.commons.objects.Client;
import co.chatchain.commons.objects.ClientUser;
import co.chatchain.commons.objects.Group;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.List;

public class UserEventMessage
{

    @NotNull
    private Client sendingClient;
    @NotNull
    private ClientUser clientUser;
    @NotNull
    private String clientId;
    @NotNull
    private Group group;
    @Nullable
    private List<Group> groups;
    @Nullable
    private String event;
    @Nullable
    private Map<String, String> EventData;

    public UserEventMessage(@NotNull final Client sendingClient, @NotNull final ClientUser clientUser, @NotNull final String clientId, @NotNull final Group group)
    {
        this.sendingClient = sendingClient;
        this.clientUser = clientUser;
        this.clientId = clientId;
        this.group = group;
    }

    public UserEventMessage(@NotNull final Client sendingClient, @NotNull final ClientUser clientUser, @NotNull final String clientId, @NotNull final Group group, @Nullable final List<Group> groups, @Nullable final String event, @Nullable final Map<String, String> eventData)
    {
        this.sendingClient = sendingClient;
        this.clientUser = clientUser;
        this.clientId = clientId;
        this.group = group;
        this.groups = groups;
        this.event = event;
        EventData = eventData;
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
    public ClientUser getClientUser()
    {
        return clientUser;
    }

    public void setClientUser(@NotNull final ClientUser clientUser)
    {
        this.clientUser = clientUser;
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
    public Group getGroup()
    {
        return group;
    }

    public void setGroup(@NotNull final Group group)
    {
        this.group = group;
    }

    @Nullable
    public List<Group> getGroups()
    {
        return groups;
    }

    public void setGroups(@Nullable final List<Group> groups)
    {
        this.groups = groups;
    }

    @Nullable
    public String getEvent()
    {
        return event;
    }

    public void setEvent(@Nullable final String event)
    {
        this.event = event;
    }

    @Nullable
    public Map<String, String> getEventData()
    {
        return EventData;
    }

    public void setEventData(@Nullable final Map<String, String> eventData)
    {
        EventData = eventData;
    }
}
