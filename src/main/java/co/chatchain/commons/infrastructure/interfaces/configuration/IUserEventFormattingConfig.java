package co.chatchain.commons.infrastructure.interfaces.configuration;

import co.chatchain.commons.core.entities.messages.UserEventMessage;

import java.util.List;

public interface IUserEventFormattingConfig
{
    List<String> getUserEventFormattingString(final UserEventMessage message);
}
