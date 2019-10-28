package co.chatchain.commons.objects.requests;

import co.chatchain.commons.objects.ClientUser;

import java.util.Dictionary;

public class UserEventRequest
{

    private ClientUser clientUser;
    private String event;
    private Dictionary<String, String> eventData;

    public UserEventRequest()
    {
    }

    public UserEventRequest(final ClientUser clientUser, final String event, final Dictionary<String, String> eventData)
    {
        this.clientUser = clientUser;
        this.event = event;
        this.eventData = eventData;
    }

    public ClientUser getClientUser()
    {
        return clientUser;
    }

    public void setClientUser(final ClientUser clientUser)
    {
        this.clientUser = clientUser;
    }

    public String getEvent()
    {
        return event;
    }

    public void setEvent(final String event)
    {
        this.event = event;
    }

    public Dictionary<String, String> getEventData()
    {
        return eventData;
    }

    public void setEventData(final Dictionary<String, String> eventData)
    {
        this.eventData = eventData;
    }
}
