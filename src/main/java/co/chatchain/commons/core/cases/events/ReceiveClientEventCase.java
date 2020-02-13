package co.chatchain.commons.core.cases.events;

import co.chatchain.commons.core.entities.messages.events.ClientEventMessage;
import co.chatchain.commons.core.interfaces.IMessageSender;
import co.chatchain.commons.core.interfaces.cases.events.IReceiveClientEventCase;
import co.chatchain.commons.core.interfaces.formatters.events.IClientEventFormatter;

import javax.inject.Inject;

public class ReceiveClientEventCase implements IReceiveClientEventCase
{
    private final IMessageSender messageSender;
    private final IClientEventFormatter clientEventFormatter;

    @Inject
    public ReceiveClientEventCase(final IMessageSender messageSender, final IClientEventFormatter clientEventFormatter)
    {
        this.messageSender = messageSender;
        this.clientEventFormatter = clientEventFormatter;
    }

    @Override
    public boolean handle(final ClientEventMessage message)
    {
        return messageSender.sendMessage(clientEventFormatter.format(message), message.getGroup());
    }
}
