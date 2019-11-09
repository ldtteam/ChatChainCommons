package co.chatchain.commons.objects.requests;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ClientEventRequest
{

    @Nullable
    private String event;
    @Nullable
    private Map<String, String> EventData;

    public ClientEventRequest()
    {
    }

    public ClientEventRequest(@Nullable final String event, @Nullable final Map<String, String> eventData)
    {
        this.event = event;
        EventData = eventData;
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
