package co.chatchain.commons.infrastructure.interfaces.configuration;

import co.chatchain.commons.core.entities.messages.GenericMessageMessage;

import java.util.List;

public interface IGenericMessageFormattingConfig
{
    List<String> getGenericMessageFormattingString(final GenericMessageMessage message);
}
