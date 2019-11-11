package co.chatchain.commons.configuration.overrides;

import co.chatchain.commons.configuration.FormatAction;
import co.chatchain.commons.configuration.formats.MessageFormats;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("CanBeFinal")
@ConfigSerializable
public class ClientOverrides
{
    @Setting("formats")
    private MessageFormats formats = new MessageFormats();

    @Nullable
    public List<String> getUsernameOverride(final String userId, final FormatAction action)
    {
        return userOverrides.getOrDefault(userId, null) == null ? null : action.invoke(userOverrides.getOrDefault(userId, null).getFormats());
    }

    @Setting("user-overrides")
    private Map<String, UserOverrides> userOverrides = new HashMap<>();

    public MessageFormats getFormats()
    {
        return formats;
    }

    public Map<String, UserOverrides> getUserOverrides()
    {
        return userOverrides;
    }
}
