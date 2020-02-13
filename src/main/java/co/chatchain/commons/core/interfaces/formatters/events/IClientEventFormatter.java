package co.chatchain.commons.core.interfaces.formatters.events;

import co.chatchain.commons.core.entities.messages.events.ClientEventMessage;

public interface IClientEventFormatter
{
    String format(ClientEventMessage message);
}
