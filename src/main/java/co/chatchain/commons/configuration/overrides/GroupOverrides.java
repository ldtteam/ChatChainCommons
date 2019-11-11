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
public class GroupOverrides
{
    @Setting("formats")
    private MessageFormats formats = new MessageFormats();

    @Nullable
    public List<String> getUsernameOverride(final String clientId, final String userId, final FormatAction action)
    {
        final ClientOverrides clientOverride = clientOverrides.getOrDefault(clientId, null);
        final List<String> override = clientOverride == null ? null : clientOverride.getUsernameOverride(userId, action);

        if (override != null)
            return override;

        return userOverrides.getOrDefault(userId, null) == null ? null : action.invoke(userOverrides.getOrDefault(userId, null).getFormats());
    }

    @Nullable
    public List<String> getClientOverride(final String clientId, final FormatAction action)
    {
        return clientOverrides.getOrDefault(clientId, null) == null ? null : action.invoke(clientOverrides.getOrDefault(clientId, null).getFormats());
    }

    @Setting("user-overrides")
    private Map<String, UserOverrides> userOverrides = new HashMap<>();

    @Setting("client-overrides")
    private Map<String, ClientOverrides> clientOverrides = new HashMap<>();

    public MessageFormats getFormats()
    {
        return formats;
    }

    public Map<String, UserOverrides> getUserOverrides()
    {
        return userOverrides;
    }

    public Map<String, ClientOverrides> getClientOverrides()
    {
        return clientOverrides;
    }
}