package co.chatchain.commons.messages.objects.messages;

import co.chatchain.commons.messages.abstracts.messages.AbstractClientEventMessage;
import co.chatchain.commons.messages.objects.Client;

import java.util.Map;

public class ClientEventMessage extends AbstractClientEventMessage<Client>
{

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
}
