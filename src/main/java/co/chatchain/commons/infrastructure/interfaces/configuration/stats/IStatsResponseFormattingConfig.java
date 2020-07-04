package co.chatchain.commons.infrastructure.interfaces.configuration.stats;

import co.chatchain.commons.core.entities.messages.stats.StatsResponseMessage;

import java.util.List;

public interface IStatsResponseFormattingConfig
{
    List<String> getStatsResponseFormattingString(final StatsResponseMessage message);
}
