package co.chatchain.commons.infrastructure.interfaces.configuration.events;

import co.chatchain.commons.core.entities.messages.events.ClientEventMessage;

import java.util.List;

public interface IClientEventFormattingConfig
{
    List<String> getClientEventFormattingString(final ClientEventMessage message);
}
