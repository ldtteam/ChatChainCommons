package co.chatchain.commons.messages.abstracts.messages;

import co.chatchain.commons.messages.abstracts.AbstractClient;

import java.util.Map;

public abstract class AbstractClientEventMessage<T1 extends AbstractClient>
{

    protected String event;
    protected T1 sendingClient;
    protected boolean sendToSelf;
    protected Map<String, String> extraEventData;

    public String getEvent()
    {
        return this.event;
    }

    public void setEvent(final String event)
    {
        this.event = event;
    }

    public T1 getSendingClient()
    {
        return this.sendingClient;
    }

    public void setSendingClient(final T1 sendingClient)
    {
        this.sendingClient = sendingClient;
    }

    public boolean hasSendToSelf()
    {
        return this.sendToSelf;
    }

    public void setSendToSelf(final Boolean sendToSelf)
    {
        this.sendToSelf = sendToSelf;
    }

    public Map<String, String> getExtraEventData()
    {
        return this.extraEventData;
    }

    public void setExtraEventData(final Map<String, String> extraEventData)
    {
        this.extraEventData = extraEventData;
    }

    public void addExtraEvent(final String extraEventName, final String extraEventData)
    {
        this.extraEventData.put(extraEventName, extraEventData);
    }

}
