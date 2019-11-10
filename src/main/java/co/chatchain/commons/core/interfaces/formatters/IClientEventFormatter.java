package co.chatchain.commons.core.interfaces.formatters;

import co.chatchain.commons.core.entites.messages.ClientEventMessage;

public interface IClientEventFormatter
{
    String format(ClientEventMessage message);
}
