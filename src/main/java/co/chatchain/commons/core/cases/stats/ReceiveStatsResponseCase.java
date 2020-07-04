package co.chatchain.commons.core.cases.stats;

import co.chatchain.commons.core.entities.messages.stats.StatsResponseMessage;
import co.chatchain.commons.core.interfaces.IMessageSender;
import co.chatchain.commons.core.interfaces.cases.stats.IReceiveStatsResponseCase;
import co.chatchain.commons.core.interfaces.formatters.stats.IStatsResponseFormatter;
import co.chatchain.commons.interfaces.IChatChainHubConnection;

import javax.inject.Inject;

public class ReceiveStatsResponseCase implements IReceiveStatsResponseCase
{
    protected final IChatChainHubConnection chatChainHubConnection;
    protected final IMessageSender messageSender;
    protected final IStatsResponseFormatter statsResponseFormatter;

    @Inject
    public ReceiveStatsResponseCase(final IChatChainHubConnection chatChainHubConnection, final IMessageSender messageSender, final IStatsResponseFormatter statsResponseFormatter)
    {
        this.chatChainHubConnection = chatChainHubConnection;
        this.messageSender = messageSender;
        this.statsResponseFormatter = statsResponseFormatter;
    }

    @Override
    public boolean handle(final StatsResponseMessage message)
    {
        final String responseLocation = chatChainHubConnection.getStatsRequest(message.getRequestId());

        if (responseLocation != null)
        {
            if (message.getStatsObject().getOnlineUsers() == null &&
                    message.getStatsObject().getPerformance() == null &&
                    message.getStatsObject().getPerformanceName() == null)
            {
                return true;
            }

            return messageSender.sendStatsMessage(statsResponseFormatter.format(message), responseLocation);
        }

        return false;
    }
}
