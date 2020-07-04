package co.chatchain.commons.infrastructure.formatters.events;

import co.chatchain.commons.core.entities.ClientRank;
import co.chatchain.commons.core.entities.messages.events.UserEventMessage;
import co.chatchain.commons.core.interfaces.formatters.events.IUserEventFormatter;
import co.chatchain.commons.infrastructure.interfaces.configuration.events.IUserEventFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.replacements.IClientRankReplacements;
import co.chatchain.commons.infrastructure.interfaces.replacements.IClientReplacements;
import co.chatchain.commons.infrastructure.interfaces.replacements.IClientUserReplacements;
import co.chatchain.commons.infrastructure.interfaces.replacements.IGroupReplacements;
import co.chatchain.commons.infrastructure.utils.FormattingUtils;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

public class UserEventFormatter implements IUserEventFormatter
{
    private final IUserEventFormattingConfig userEventFormattingConfig;
    private final IGroupReplacements groupReplacements;
    private final IClientReplacements clientReplacements;
    private final IClientRankReplacements clientRankReplacements;
    private final IClientUserReplacements clientUserReplacements;

    @Inject
    public UserEventFormatter(final IUserEventFormattingConfig userEventFormattingConfig, final IGroupReplacements groupReplacements, final IClientReplacements clientReplacements, final IClientRankReplacements clientRankReplacements, final IClientUserReplacements clientUserReplacements)
    {
        this.userEventFormattingConfig = userEventFormattingConfig;
        this.groupReplacements = groupReplacements;
        this.clientReplacements = clientReplacements;
        this.clientRankReplacements = clientRankReplacements;
        this.clientUserReplacements = clientUserReplacements;
    }

    @Override
    public String format(final UserEventMessage message)
    {
        return FormattingUtils.format(userEventFormattingConfig.getUserEventFormattingString(message), replacementString -> childReplacements(replacementString, message));
    }

    private String childReplacements(final String replacementString, final UserEventMessage message)
    {
        String returnString = groupReplacements.getReplacementObject(message.getGroup(), replacementString);
        if (returnString != null)
            return returnString;

        returnString = clientReplacements.getReplacementObject(message.getSendingClient(), replacementString);
        if (returnString != null)
            return returnString;

        final List<ClientRank> clientRanks = message.getClientUser().getClientRanks();

        if (clientRanks != null)
        {
            clientRanks.sort(Comparator.comparingInt(ClientRank::getPriority));

            final ClientRank rank = clientRanks.stream().findFirst().orElse(null);

            returnString = clientRankReplacements.getReplacementObject(rank, replacementString);
            if (returnString != null)
                return returnString;
        }
        returnString = clientUserReplacements.getReplacementObject(message.getClientUser(), replacementString);
        return returnString;
    }
}
