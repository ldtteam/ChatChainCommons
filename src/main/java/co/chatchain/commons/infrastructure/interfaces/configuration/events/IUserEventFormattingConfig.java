package co.chatchain.commons.infrastructure.interfaces.configuration.events;

import co.chatchain.commons.core.entities.messages.events.UserEventMessage;

import java.util.List;

public interface IUserEventFormattingConfig
{
    List<String> getUserEventFormattingString(final UserEventMessage message);
}
