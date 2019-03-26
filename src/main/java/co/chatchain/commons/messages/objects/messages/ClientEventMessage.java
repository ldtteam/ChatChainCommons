package co.chatchain.commons.messages.objects.messages;

import co.chatchain.commons.messages.interfaces.messages.IClientEventMessage;
import co.chatchain.commons.messages.objects.Client;

import java.util.Map;

public class ClientEventMessage implements IClientEventMessage<Client>
{
    private String event;
    private Client sendingClient;
    private boolean sendToSelf;
    private Map<String, String> extraEventData;

    public ClientEventMessage(final String event, final Boolean sendToSelf, final Map<String, String> extraEventData)
    {
        this.event = event;
        this.sendToSelf = sendToSelf;
        this.extraEventData = extraEventData;
    }

    public ClientEventMessage(final String event, final Boolean sendToSelf)
    {
        this(event, sendToSelf, null);
    }

    public ClientEventMessage(final String event)
    {
        this(event, false);
    }

    @Override
    public String getEvent()
    {
        return this.event;
    }

    @Override
    public Client getSendingClient()
    {
        return this.sendingClient;
    }

    @Override
    public boolean hasSendToSelf()
    {
        return this.sendToSelf;
    }

    @Override
    public Map<String, String> getExtraEventData()
    {
        return this.extraEventData;
    }
}
