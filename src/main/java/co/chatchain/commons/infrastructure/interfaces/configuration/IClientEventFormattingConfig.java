package co.chatchain.commons.infrastructure.interfaces.configuration;

import co.chatchain.commons.core.entities.messages.ClientEventMessage;

import java.util.List;

public interface IClientEventFormattingConfig
{
    List<String> getClientEventFormattingString(final ClientEventMessage message);
}
