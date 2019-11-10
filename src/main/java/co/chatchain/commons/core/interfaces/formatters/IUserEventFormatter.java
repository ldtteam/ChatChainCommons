package co.chatchain.commons.core.interfaces.formatters;

import co.chatchain.commons.core.entites.messages.UserEventMessage;

public interface IUserEventFormatter
{
    String format(UserEventMessage message);
}
