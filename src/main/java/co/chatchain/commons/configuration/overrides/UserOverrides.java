package co.chatchain.commons.configuration.overrides;

import co.chatchain.commons.configuration.formats.MessageFormats;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@SuppressWarnings("CanBeFinal")
@ConfigSerializable
public class UserOverrides
{
    @Setting("formats")
    private MessageFormats formats = new MessageFormats();

    public MessageFormats getFormats()
    {
        return formats;
    }
}