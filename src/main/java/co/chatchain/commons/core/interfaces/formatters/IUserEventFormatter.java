package co.chatchain.commons.core.interfaces.formatters;

import co.chatchain.commons.core.entities.messages.UserEventMessage;

public interface IUserEventFormatter
{
    String format(UserEventMessage message);
}
