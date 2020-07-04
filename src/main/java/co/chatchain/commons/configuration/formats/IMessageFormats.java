package co.chatchain.commons.configuration.formats;

import co.chatchain.commons.configuration.IJsonSerializable;
import co.chatchain.commons.core.entities.messages.GenericMessageMessage;
import co.chatchain.commons.core.entities.messages.events.ClientEventMessage;
import co.chatchain.commons.core.entities.messages.events.UserEventMessage;
import co.chatchain.commons.core.entities.messages.stats.StatsResponseMessage;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IMessageFormats extends IJsonSerializable
{
    @Nullable
    List<String> getGenericMessage(final GenericMessageMessage message);

    @Nullable
    List<String> getClientEventMessages(final ClientEventMessage message);

    @Nullable
    List<String> getUserEventMessages(final UserEventMessage message);

    @Nullable
    List<String> getStatsResponses(StatsResponseMessage message);
}
