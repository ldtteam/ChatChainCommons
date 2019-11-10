package co.chatchain.commons.infrastructure.interfaces.configuration;

import co.chatchain.commons.core.entites.messages.GenericMessageMessage;

public interface IGenericMessageFormattingConfig
{
    String[] getGenericMessageFormattingString(final GenericMessageMessage message);
}
