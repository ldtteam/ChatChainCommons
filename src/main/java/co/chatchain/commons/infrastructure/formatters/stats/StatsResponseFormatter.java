package co.chatchain.commons.infrastructure.formatters.stats;

import co.chatchain.commons.core.entities.messages.stats.StatsResponseMessage;
import co.chatchain.commons.core.interfaces.formatters.stats.IStatsResponseFormatter;
import co.chatchain.commons.infrastructure.interfaces.configuration.stats.IStatsResponseFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.replacements.IClientReplacements;
import co.chatchain.commons.infrastructure.interfaces.replacements.IStatsObjectReplacements;
import co.chatchain.commons.infrastructure.utils.FormattingUtils;

import javax.inject.Inject;

public class StatsResponseFormatter implements IStatsResponseFormatter
{
    private final IStatsResponseFormattingConfig statsResponseFormattingConfig;
    private final IStatsObjectReplacements statsObjectReplacements;
    private final IClientReplacements clientReplacements;

    @Inject
    public StatsResponseFormatter(final IStatsResponseFormattingConfig statsResponseFormattingConfig, final IStatsObjectReplacements statsObjectReplacements, final IClientReplacements clientReplacements)
    {
        this.statsResponseFormattingConfig = statsResponseFormattingConfig;
        this.statsObjectReplacements = statsObjectReplacements;
        this.clientReplacements = clientReplacements;
    }

    @Override
    public String format(final StatsResponseMessage message)
    {
        return FormattingUtils.format(statsResponseFormattingConfig.getStatsResponseFormattingString(message), replacementString -> childReplacements(replacementString, message));
    }

    private String childReplacements(final String replacementString, final StatsResponseMessage message)
    {
        return clientReplacements.getReplacementObject(message.getSendingClient(), replacementString) == null ? statsObjectReplacements.getReplacementObject(message.getStatsObject(), replacementString) : null;
    }
}
