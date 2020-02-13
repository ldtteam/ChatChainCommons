package co.chatchain.commons.core.cases;

import co.chatchain.commons.core.entities.messages.GenericMessageMessage;
import co.chatchain.commons.core.interfaces.IMessageSender;
import co.chatchain.commons.core.interfaces.cases.IReceiveGenericMessageCase;
import co.chatchain.commons.core.interfaces.formatters.IGenericMessageFormatter;

import javax.inject.Inject;

public class ReceiveGenericMessageCase implements IReceiveGenericMessageCase
{
    private final IMessageSender messageSender;
    private final IGenericMessageFormatter genericMessageFormatter;

    @Inject
    public ReceiveGenericMessageCase(final IMessageSender messageSender, final IGenericMessageFormatter genericMessageFormatter)
    {
        this.messageSender = messageSender;
        this.genericMessageFormatter = genericMessageFormatter;
    }

    @Override
    public boolean handle(final GenericMessageMessage message)
    {
        return messageSender.sendMessage(genericMessageFormatter.format(message), message.getGroup());
    }
}
