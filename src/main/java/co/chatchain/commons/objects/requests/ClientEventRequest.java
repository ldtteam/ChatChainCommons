package co.chatchain.commons.objects.requests;

import java.util.Map;

public class ClientEventRequest
{

    private String event;
    private Map<String, String> EventData;

    public ClientEventRequest()
    {
    }

    public ClientEventRequest(final String event, final Map<String, String> eventData)
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

    public Map<String, String> getEventData()
    {
        return EventData;
    }

    public void setEventData(final Map<String, String> eventData)
    {
        EventData = eventData;
    }
}
