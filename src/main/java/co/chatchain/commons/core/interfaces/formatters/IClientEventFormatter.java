package co.chatchain.commons.core.interfaces.formatters;

import co.chatchain.commons.core.entities.messages.ClientEventMessage;

public interface IClientEventFormatter
{
    String format(ClientEventMessage message);
}
