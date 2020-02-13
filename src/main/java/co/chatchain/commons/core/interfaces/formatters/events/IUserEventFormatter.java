package co.chatchain.commons.core.interfaces.formatters.events;

import co.chatchain.commons.core.entities.messages.events.UserEventMessage;

public interface IUserEventFormatter
{
    String format(UserEventMessage message);
}
