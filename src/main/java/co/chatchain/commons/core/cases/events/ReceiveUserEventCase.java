package co.chatchain.commons.core.cases.events;

import co.chatchain.commons.core.entities.messages.events.UserEventMessage;
import co.chatchain.commons.core.interfaces.IMessageSender;
import co.chatchain.commons.core.interfaces.cases.events.IReceiveUserEventCase;
import co.chatchain.commons.core.interfaces.formatters.events.IUserEventFormatter;

import javax.inject.Inject;

public class ReceiveUserEventCase implements IReceiveUserEventCase
{
    private final IMessageSender messageSender;
    private final IUserEventFormatter userEventFormatter;

    @Inject
    public ReceiveUserEventCase(final IMessageSender messageSender, final IUserEventFormatter userEventFormatter)
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
