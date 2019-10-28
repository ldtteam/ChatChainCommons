package co.chatchain.commons.objects.requests;

import java.util.Dictionary;

public class ClientEventRequest
{

    private String event;
    private Dictionary<String, String> EventData;

    public ClientEventRequest()
    {
    }

    public ClientEventRequest(final String event, final Dictionary<String, String> eventData)
    {
        this.event = event;
        EventData = eventData;
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
        return EventData;
    }

    public void setEventData(final Dictionary<String, String> eventData)
    {
        EventData = eventData;
    }
}
