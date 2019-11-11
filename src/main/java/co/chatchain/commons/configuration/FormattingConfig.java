package co.chatchain.commons.configuration;

import co.chatchain.commons.configuration.formats.DefaultFormats;
import co.chatchain.commons.configuration.formats.MessageFormats;
import co.chatchain.commons.core.entities.messages.ClientEventMessage;
import co.chatchain.commons.core.entities.messages.GenericMessageMessage;
import co.chatchain.commons.core.entities.messages.UserEventMessage;
import co.chatchain.commons.infrastructure.interfaces.configuration.IFormattingConfig;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CanBeFinal")
@ConfigSerializable
public class FormattingConfig extends AbstractConfig implements IFormattingConfig
{

    @Setting("default-formats")
    private MessageFormats formats = new DefaultFormats();

    @Override
    public List<String> getClientEventFormattingString(final ClientEventMessage message)
    {
        if (message.getEvent() == null || !formats.getClientEventMessages().containsKey(message.getEvent().toUpperCase()))
            return new ArrayList<>();

        return formats.getClientEventMessages().get(message.getEvent().toUpperCase());
    }

    @Override
    public List<String> getGenericMessageFormattingString(final GenericMessageMessage message)
    {
        return formats.getGenericMessage();
    }

    @Override
    public List<String> getUserEventFormattingString(final UserEventMessage message)
    {
        if (message.getEvent() == null || !formats.getUserEventMessages().containsKey(message.getEvent().toUpperCase()))
            return new ArrayList<>();

        return formats.getUserEventMessages().get(message.getEvent().toUpperCase());
    }
}
