package co.chatchain.commons.core.interfaces.formatters.stats;

import co.chatchain.commons.core.entities.messages.stats.StatsResponseMessage;

public interface IStatsResponseFormatter
{
    String format(StatsResponseMessage message);
}
