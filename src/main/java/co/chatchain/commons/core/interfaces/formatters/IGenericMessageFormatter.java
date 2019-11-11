package co.chatchain.commons.core.interfaces.formatters;

import co.chatchain.commons.core.entities.messages.GenericMessageMessage;

public interface IGenericMessageFormatter
{
    String format(GenericMessageMessage message);
}
