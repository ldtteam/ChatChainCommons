package co.chatchain.commons.messages.objects.message;

import co.chatchain.commons.messages.interfaces.message.IClientEventMessage;
import co.chatchain.commons.messages.objects.Client;

public class ClientEventMessage implements IClientEventMessage<Client>
{
    private String event;
    private Client sendingClient;
    private boolean sendToSelf;

    public ClientEventMessage(final String event, final Boolean sendToSelf)
    {
        this.event = event;
        this.sendToSelf = sendToSelf;
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
    public Client getClient()
    {
        return this.sendingClient;
    }

    @Override
    public boolean hasSendToSelf()
    {
        return this.sendToSelf;
    }
}
