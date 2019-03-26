package co.chatchain.commons.messages.abstracts.messages;

import co.chatchain.commons.messages.objects.Client;
import co.chatchain.commons.messages.objects.User;

import java.util.Map;

public class AbstractUserEventMessage<T1 extends User, T2 extends Client>
{

    protected String event;
    protected T1 user;
    protected T2 sendingClient;
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

    public T1 getUser()
    {
        return this.user;
    }

    public void setUser(final T1 user)
    {
        this.user = user;
    }

    public T2 getSendingClient()
    {
        return this.sendingClient;
    }

    public void setSendingClient(final T2 sendingClient)
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
