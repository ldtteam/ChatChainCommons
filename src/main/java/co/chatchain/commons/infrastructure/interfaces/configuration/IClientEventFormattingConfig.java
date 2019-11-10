package co.chatchain.commons.infrastructure.interfaces.configuration;

import co.chatchain.commons.core.entites.messages.ClientEventMessage;

public interface IClientEventFormattingConfig
{
    String[] getClientEventFormattingString(final ClientEventMessage message);
}
