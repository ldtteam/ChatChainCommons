package co.chatchain.commons.messages.objects.message;

import co.chatchain.commons.messages.interfaces.IClient;
import co.chatchain.commons.messages.interfaces.message.IClientEventMessage;

public class ClientEventMessage<T1 extends IClient> implements IClientEventMessage<T1>
{
    private String event;
    private T1 client;
    private boolean sendToSelf;

    public ClientEventMessage(final String event, final T1 client, final Boolean sendToSelf)
    {
        this.event = event;
        this.client = client;
        this.sendToSelf = sendToSelf;
    }

    @Override
    public String getEvent()
    {
        return this.event;
    }

    @Override
    public T1 getClient()
    {
        return this.client;
    }

    @Override
    public boolean hasSendToSelf()
    {
        return this.sendToSelf;
    }
}
