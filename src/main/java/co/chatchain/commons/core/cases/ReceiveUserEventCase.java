package co.chatchain.commons.core.cases;

import co.chatchain.commons.core.entities.messages.UserEventMessage;
import co.chatchain.commons.core.interfaces.IMessageSender;
import co.chatchain.commons.core.interfaces.cases.IReceiveUserEventCase;
import co.chatchain.commons.core.interfaces.formatters.IUserEventFormatter;

import javax.inject.Inject;

public class ReceiveUserEventCase implements IReceiveUserEventCase
{
    private final IMessageSender messageSender;
    private final IUserEventFormatter userEventFormatter;

    @Inject
    public ReceiveUserEventCase(IMessageSender messageSender, IUserEventFormatter userEventFormatter)
    {
        this.messageSender = messageSender;
        this.userEventFormatter = userEventFormatter;
    }

    @Override
    public boolean handle(final UserEventMessage message)
    {
        return messageSender.sendMessage(userEventFormatter.format(message), message.getGroup());
    }
}
