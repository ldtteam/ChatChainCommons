package co.chatchain.commons.core.cases;

import co.chatchain.commons.core.entites.messages.ClientEventMessage;
import co.chatchain.commons.core.interfaces.IMessageSender;
import co.chatchain.commons.core.interfaces.cases.IReceiveClientEventCase;
import co.chatchain.commons.core.interfaces.formatters.IClientEventFormatter;

import javax.inject.Inject;

public class ReceiveClientEventCase implements IReceiveClientEventCase
{
    private final IMessageSender messageSender;
    private final IClientEventFormatter clientEventFormatter;

    @Inject
    public ReceiveClientEventCase(IMessageSender messageSender, IClientEventFormatter clientEventFormatter)
    {
        this.messageSender = messageSender;
        this.clientEventFormatter = clientEventFormatter;
    }

    @Override
    public boolean Handle(final ClientEventMessage message)
    {
        return messageSender.sendMessage(clientEventFormatter.format(message), message.getGroup());
    }
}
