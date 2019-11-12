package co.chatchain.commons.configuration;

import co.chatchain.commons.configuration.formats.DefaultFormats;
import co.chatchain.commons.configuration.formats.MessageFormats;
import co.chatchain.commons.configuration.overrides.ClientOverrides;
import co.chatchain.commons.configuration.overrides.GroupOverrides;
import co.chatchain.commons.configuration.overrides.UserOverrides;
import co.chatchain.commons.core.entities.Client;
import co.chatchain.commons.core.entities.ClientUser;
import co.chatchain.commons.core.entities.Group;
import co.chatchain.commons.core.entities.messages.ClientEventMessage;
import co.chatchain.commons.core.entities.messages.GenericMessageMessage;
import co.chatchain.commons.core.entities.messages.UserEventMessage;
import co.chatchain.commons.infrastructure.interfaces.configuration.IFormattingConfig;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "CanBeFinal"})
@ConfigSerializable
public class AdvancedFormattingConfig extends AbstractConfig implements IFormattingConfig
{

    @Setting("default-formats")
    private MessageFormats formats = new DefaultFormats();

    @Setting("user-overrides")
    private Map<String, UserOverrides> userOverrides = new HashMap<>();

    @Setting("client-overrides")
    private Map<String, ClientOverrides> clientOverrides = new HashMap<>();

    @Setting("group-overrides")
    private Map<String, GroupOverrides> groupOverrides = new HashMap<>();

    @NotNull
    private List<String> getOverride(final Group group, final Client client, final ClientUser user, final FormatAction action)
    {
        if (group != null && client != null && user != null)
            return getOverride(group.getId(), client.getId(), user.getUniqueId(), action);

        if (group != null && client != null)
            return getOverride(group.getId(), client.getId(), action);

        if (group != null)
            return getOverride(group.getId(), action);

        return action.invoke(formats);
    }

    @Override
    public List<String> getClientEventFormattingString(final ClientEventMessage message)
    {
        if (message.getEvent() == null)
            return new ArrayList<>();

        return getOverride(message.getGroup(), message.getSendingClient(), null, formats -> formats.getClientEventMessages().get(message.getEvent().toUpperCase()));
    }

    @Override
    public List<String> getGenericMessageFormattingString(final GenericMessageMessage message)
    {
        return getOverride(message.getGroup(), message.getSendingClient(), message.getClientUser(), MessageFormats::getGenericMessage);
    }

    @Override
    public List<String> getUserEventFormattingString(final UserEventMessage message)
    {
        if (message.getEvent() == null)
            return new ArrayList<>();

        return getOverride(message.getGroup(), message.getSendingClient(), message.getClientUser(), formats -> formats.getUserEventMessages().get(message.getEvent().toUpperCase()));
    }

    @NotNull
    private List<String> getOverride(final String groupId, final String clientId, final String userId, final FormatAction action)
    {
        List<String> override = getUsernameOverride(groupId, clientId, userId, action);

        if (override != null)
            return override;

        return getOverride(groupId, clientId, action);
    }

    @NotNull
    private List<String> getOverride(final String groupId, final String clientId, final FormatAction action)
    {
        List<String> override = getClientOverride(groupId, clientId, action);

        if (override != null)
            return override;

        return getOverride(groupId, action);
    }

    @NotNull
    private List<String> getOverride(final String groupId, final FormatAction action)
    {
        List<String> override = getGroupOverride(groupId, action);

        if (override != null)
            return override;

        return action.invoke(formats);
    }

    @Nullable
    private List<String> getUsernameOverride(final String groupId, final String clientId, final String userId, final FormatAction action)
    {
        final GroupOverrides groupOverride = groupOverrides.getOrDefault(groupId, null);
        List<String> override = groupOverride == null ? null : groupOverride.getUsernameOverride(clientId, userId, action);

        if (override != null)
            return override;

        final ClientOverrides clientOverride = clientOverrides.getOrDefault(clientId, null);
        override = clientOverride == null ? null : clientOverride.getUsernameOverride(userId, action);

        if (override != null)
            return override;

        return userOverrides.getOrDefault(userId, null) == null ? null : action.invoke(userOverrides.getOrDefault(userId, null).getFormats());
    }

    @Nullable
    private List<String> getClientOverride(final String groupId, final String clientId, final FormatAction action)
    {
        final GroupOverrides groupOverride = groupOverrides.getOrDefault(groupId, null);
        final List<String> override = groupOverride == null ? null : groupOverride.getClientOverride(clientId, action);

        if (override != null)
            return override;

        return clientOverrides.getOrDefault(clientId, null) == null ? null : action.invoke(clientOverrides.getOrDefault(clientId, null).getFormats());
    }

    @Nullable
    private List<String> getGroupOverride(final String groupId, final FormatAction action)
    {
        return groupOverrides.getOrDefault(groupId, null) == null ? null : action.invoke(groupOverrides.getOrDefault(groupId, null).getFormats());
    }
}
