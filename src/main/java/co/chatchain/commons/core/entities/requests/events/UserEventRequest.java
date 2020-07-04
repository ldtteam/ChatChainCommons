package co.chatchain.commons.core.entities.requests.events;

import co.chatchain.commons.core.entities.ClientUser;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class UserEventRequest
{

    @Nullable
    private ClientUser clientUser;
    @Nullable
    private String event;
    @Nullable
    private Map<String, String> eventData;

    public UserEventRequest()
    {
    }

    public UserEventRequest(@Nullable final ClientUser clientUser, @Nullable final String event, @Nullable final Map<String, String> eventData)
    {
        this.clientUser = clientUser;
        this.event = event;
        this.eventData = eventData;
    }

    @Nullable
    public ClientUser getClientUser()
    {
        return clientUser;
    }

    public void setClientUser(@Nullable final ClientUser clientUser)
    {
        this.clientUser = clientUser;
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
        return eventData;
    }

    public void setEventData(@Nullable final Map<String, String> eventData)
    {
        this.eventData = eventData;
    }
}
