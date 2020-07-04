package co.chatchain.commons.configuration.formats;

import co.chatchain.commons.core.entities.messages.GenericMessageMessage;
import co.chatchain.commons.core.entities.messages.events.ClientEventMessage;
import co.chatchain.commons.core.entities.messages.events.UserEventMessage;
import co.chatchain.commons.core.entities.messages.stats.StatsResponseMessage;
import com.google.gson.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.chatchain.commons.configuration.formats.OverrideType.*;

public class MessageFormats implements IMessageFormats
{
    private final boolean advanced;
    private final OverrideType parentOverrideType;
    private final OverrideType overrideType;
    protected List<String> genericMessage;
    protected Map<String, List<String>> clientEventMessages;
    protected Map<String, List<String>> userEventMessages;
    protected List<String> statsResponse;

    private Map<String, MessageFormats> groupOverrides;
    private Map<String, MessageFormats> clientOverrides;
    private Map<String, MessageFormats> userOverrides;

    public MessageFormats(final boolean advanced)
    {
        this.advanced = advanced;
        this.parentOverrideType = null;
        this.overrideType = ROOT;
    }

    private MessageFormats(final boolean advanced, final OverrideType parentOverrideType, final OverrideType overrideType, final JsonElement jsonElement)
    {
        this.advanced = advanced;
        this.parentOverrideType = parentOverrideType;
        this.overrideType = overrideType;
        deserialize(jsonElement);
    }

    @Nullable
    @Override
    public List<String> getGenericMessage(final GenericMessageMessage message)
    {
        if (this.advanced)
        {
            if (this.groupOverrides != null)
            {
                final String groupId = message.getGroup().getId();
                if (this.groupOverrides.containsKey(groupId))
                {
                    final List<String> groupOverride = this.groupOverrides.get(groupId).getGenericMessage(message);
                    if (groupOverride != null && !groupOverride.isEmpty())
                    {
                        return groupOverride;
                    }
                }
            }

            if (this.clientOverrides != null)
            {
                final String clientId = message.getSendingClient().getId();
                if (this.clientOverrides.containsKey(clientId))
                {
                    final List<String> clientOverride = this.clientOverrides.get(clientId).getGenericMessage(message);
                    if (clientOverride != null && !clientOverride.isEmpty())
                    {
                        return clientOverride;
                    }
                }
            }

            if (message.getClientUser() != null && this.userOverrides != null)
            {
                final String userId = message.getClientUser().getUniqueId();
                if (this.userOverrides.containsKey(userId))
                {
                    final List<String> userOverride = this.userOverrides.get(userId).getGenericMessage(message);
                    if (userOverride != null && !userOverride.isEmpty())
                    {
                        return userOverride;
                    }
                }
            }
        }

        return this.genericMessage;
    }

    @Nullable
    @Override
    public List<String> getClientEventMessages(final ClientEventMessage message)
    {
        if (this.advanced)
        {
            if (this.groupOverrides != null)
            {
                final String groupId = message.getGroup().getId();
                if (this.groupOverrides.containsKey(groupId))
                {
                    final List<String> groupOverride = this.groupOverrides.get(groupId).getClientEventMessages(message);
                    if (groupOverride != null && !groupOverride.isEmpty())
                    {
                        return groupOverride;
                    }
                }
            }

            if (this.clientOverrides != null)
            {
                final String clientId = message.getSendingClient().getId();
                if (this.clientOverrides.containsKey(clientId))
                {
                    final List<String> clientOverride = this.clientOverrides.get(clientId).getClientEventMessages(message);
                    if (clientOverride != null && !clientOverride.isEmpty())
                    {
                        return clientOverride;
                    }
                }
            }
        }

        return this.clientEventMessages == null ? null : clientEventMessages.getOrDefault(message.getEvent(), null);
    }

    @Nullable
    @Override
    public List<String> getUserEventMessages(final UserEventMessage message)
    {
        if (this.advanced)
        {
            if (groupOverrides != null)
            {
                final String groupId = message.getGroup().getId();
                if (groupOverrides.containsKey(groupId))
                {
                    final List<String> groupOverride = groupOverrides.get(groupId).getUserEventMessages(message);
                    if (groupOverride != null && !groupOverride.isEmpty())
                    {
                        return groupOverride;
                    }
                }
            }

            if (clientOverrides != null)
            {
                final String clientId = message.getSendingClient().getId();
                if (clientOverrides.containsKey(clientId))
                {
                    final List<String> clientOverride = clientOverrides.get(clientId).getUserEventMessages(message);
                    if (clientOverride != null && !clientOverride.isEmpty())
                    {
                        return clientOverride;
                    }
                }
            }

            if (userOverrides != null)
            {
                final String userId = message.getClientUser().getUniqueId();
                if (userOverrides.containsKey(userId))
                {
                    final List<String> userOverride = userOverrides.get(userId).getUserEventMessages(message);
                    if (userOverride != null && !userOverride.isEmpty())
                    {
                        return userOverride;
                    }
                }
            }
        }

        return this.userEventMessages == null ? null : userEventMessages.getOrDefault(message.getEvent(), null);
    }

    @Nullable
    @Override
    public List<String> getStatsResponses(final StatsResponseMessage message)
    {
        if (this.advanced)
        {
            if (clientOverrides != null)
            {
                final String clientId = message.getSendingClient().getId();
                if (clientOverrides.containsKey(clientId))
                {
                    final List<String> clientOverride = clientOverrides.get(clientId).getStatsResponses(message);
                    if (clientOverride != null && !clientOverride.isEmpty())
                    {
                        return clientOverride;
                    }
                }
            }
        }

        return this.statsResponse;
    }

    @NotNull
    @Override
    public JsonElement serialize()
    {
        final JsonObject savedElement = new JsonObject();

        if (this.genericMessage != null)
        {
            final JsonArray genericMessageArray = new JsonArray();
            for (final String genericMessageEntry : this.genericMessage)
            {
                genericMessageArray.add(genericMessageEntry);
            }
            savedElement.add("generic-message", genericMessageArray);
        }

        if (this.overrideType != USER && this.clientEventMessages != null)
        {
            final JsonObject clientEventMessageObject = new JsonObject();
            for (final Map.Entry<String, List<String>> clientEventMessageEntry : this.clientEventMessages.entrySet())
            {
                final JsonArray clientEventMessageArray = new JsonArray();
                for (final String clientEventMessageArrayEntry : clientEventMessageEntry.getValue())
                {
                    clientEventMessageArray.add(clientEventMessageArrayEntry);
                }
                clientEventMessageObject.add(clientEventMessageEntry.getKey(), clientEventMessageArray);
            }
            savedElement.add("client-event-message", clientEventMessageObject);
        }

        if (this.userEventMessages != null)
        {
            final JsonObject userEventMessageObject = new JsonObject();
            for (final Map.Entry<String, List<String>> userEventMessageEntry : this.userEventMessages.entrySet())
            {
                final JsonArray userEventMessageArray = new JsonArray();
                for (final String userEventMessageArrayEntry : userEventMessageEntry.getValue())
                {
                    userEventMessageArray.add(userEventMessageArrayEntry);
                }
                userEventMessageObject.add(userEventMessageEntry.getKey(), userEventMessageArray);
            }
            savedElement.add("user-event-message", userEventMessageObject);
        }

        if ((this.overrideType == ROOT || (this.overrideType == CLIENT && this.parentOverrideType == ROOT)) && this.statsResponse != null)
        {
            final JsonArray statsResponseArray = new JsonArray();
            for (final String statsResponseEntry : this.statsResponse)
            {
                statsResponseArray.add(statsResponseEntry);
            }
            savedElement.add("stats-response", statsResponseArray);
        }

        if (this.advanced)
        {
            if (this.overrideType == ROOT && this.groupOverrides == null)
            {
                this.groupOverrides = new HashMap<>();
            }

            if (this.overrideType == ROOT)
            {
                final JsonObject groupOverridesObject = new JsonObject();
                for (final Map.Entry<String, MessageFormats> overrideEntry : this.groupOverrides.entrySet())
                {
                    groupOverridesObject.add(overrideEntry.getKey(), overrideEntry.getValue().serialize());
                }
                savedElement.add("group-overrides", groupOverridesObject);
            }

            if (this.clientOverrides == null && this.overrideType == ROOT)
            {
                this.clientOverrides = new HashMap<>();
            }
            if ((this.overrideType == ROOT || this.overrideType == GROUP) && this.clientOverrides != null)
            {
                final JsonObject clientOverridesObject = new JsonObject();
                for (final Map.Entry<String, MessageFormats> overrideEntry : this.clientOverrides.entrySet())
                {
                    clientOverridesObject.add(overrideEntry.getKey(), overrideEntry.getValue().serialize());
                }
                savedElement.add("client-overrides", clientOverridesObject);
            }

            if (this.userOverrides == null && this.overrideType == ROOT)
            {
                this.userOverrides = new HashMap<>();
            }

            if (this.overrideType != USER && this.userOverrides != null)
            {
                final JsonObject userOverrideObject = new JsonObject();
                for (final Map.Entry<String, MessageFormats> overrideEntry : this.userOverrides.entrySet())
                {
                    userOverrideObject.add(overrideEntry.getKey(), overrideEntry.getValue().serialize());
                }
                savedElement.add("user-overrides", userOverrideObject);
            }
        }

        return savedElement;
    }

    @Override
    public void deserialize(@NotNull final JsonElement jsonElement)
    {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        if (jsonObject.has("generic-message"))
        {
            this.genericMessage = new ArrayList<>();
            for (final JsonElement element : jsonObject.getAsJsonArray("generic-message"))
            {
                this.genericMessage.add(element.getAsString());
            }
        }

        if (this.overrideType != USER && jsonObject.has("client-event-message"))
        {
            this.clientEventMessages = new HashMap<>();
            for (final String event : jsonObject.getAsJsonObject("client-event-message").keySet())
            {
                final JsonArray eventJsonArray = jsonObject.getAsJsonObject("client-event-message").getAsJsonArray(event);
                if (eventJsonArray.size() > 0)
                {
                    this.clientEventMessages.put(event, new ArrayList<>());
                    for (final JsonElement element : eventJsonArray)
                    {
                        this.clientEventMessages.get(event).add(element.getAsString());
                    }
                }
            }
        }

        if (jsonObject.has("user-event-message"))
        {
            this.userEventMessages = new HashMap<>();
            final JsonObject clientEventMessageObject = jsonObject.getAsJsonObject("user-event-message");
            for (final String event : clientEventMessageObject.keySet())
            {
                final JsonArray eventJsonArray = clientEventMessageObject.getAsJsonArray(event);
                if (eventJsonArray.size() > 0)
                {
                    this.userEventMessages.put(event, new ArrayList<>());
                    for (final JsonElement element : eventJsonArray)
                    {
                        this.userEventMessages.get(event).add(element.getAsString());
                    }
                }
            }
        }

        if ((this.overrideType == ROOT || (this.overrideType == CLIENT && this.parentOverrideType == ROOT))
                && jsonObject.has("stats-response"))
        {
            this.statsResponse = new ArrayList<>();
            for (final JsonElement element : jsonObject.getAsJsonArray("stats-response"))
            {
                this.statsResponse.add(element.getAsString());
            }
        }

        if (this.advanced)
        {

            if (this.overrideType == ROOT && jsonObject.has("group-overrides"))
            {
                this.groupOverrides = new HashMap<>();
                final JsonObject groupOverridesObject = jsonObject.getAsJsonObject("group-overrides");
                for (final Map.Entry<String, JsonElement> overrideEntry : groupOverridesObject.entrySet())
                {
                    this.groupOverrides.put(overrideEntry.getKey(), new MessageFormats(true, this.overrideType, GROUP, overrideEntry.getValue()));
                }
            }

            if ((this.overrideType == ROOT || this.overrideType  == GROUP) && jsonObject.has("client-overrides"))
            {
                this.clientOverrides = new HashMap<>();
                final JsonObject clientOverridesObject = jsonObject.getAsJsonObject("client-overrides");
                for (final Map.Entry<String, JsonElement> overrideEntry : clientOverridesObject.entrySet())
                {
                    this.clientOverrides.put(overrideEntry.getKey(), new MessageFormats(true, this.overrideType, CLIENT, overrideEntry.getValue()));
                }
            }

            if (this.overrideType != USER && jsonObject.has("user-overrides"))
            {
                this.userOverrides = new HashMap<>();
                final JsonObject userOverridesObject = jsonObject.getAsJsonObject("user-overrides");
                for (final Map.Entry<String, JsonElement> overrideEntry : userOverridesObject.entrySet())
                {
                    this.userOverrides.put(overrideEntry.getKey(), new MessageFormats(true, this.overrideType, USER, overrideEntry.getValue()));
                }
            }
        }
    }
}
