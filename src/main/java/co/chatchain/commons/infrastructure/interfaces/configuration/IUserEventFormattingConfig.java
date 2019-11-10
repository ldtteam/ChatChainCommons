package co.chatchain.commons.infrastructure.interfaces.configuration;

import co.chatchain.commons.core.entites.messages.UserEventMessage;

public interface IUserEventFormattingConfig
{
    String[] getUserEventFormattingString(final UserEventMessage message);
}
