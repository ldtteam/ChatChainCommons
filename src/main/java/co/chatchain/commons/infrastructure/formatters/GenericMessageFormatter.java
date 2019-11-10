package co.chatchain.commons.infrastructure.formatters;

import co.chatchain.commons.core.entites.ClientRank;
import co.chatchain.commons.core.entites.messages.GenericMessageMessage;
import co.chatchain.commons.core.interfaces.formatters.IGenericMessageFormatter;
import co.chatchain.commons.infrastructure.interfaces.configuration.IGenericMessageFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.replacements.*;
import co.chatchain.commons.infrastructure.utils.FormattingUtils;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

public class GenericMessageFormatter implements IGenericMessageFormatter
{
    private final IGenericMessageFormattingConfig genericMessageFormattingConfig;
    private final IGroupReplacements groupReplacements;
    private final IClientReplacements clientReplacements;
    private final IGenericMessageReplacements genericMessageReplacements;
    private final IClientRankReplacements clientRankReplacements;
    private final IClientUserReplacements clientUserReplacements;

    @Inject
    public GenericMessageFormatter(final IGenericMessageFormattingConfig genericMessageFormattingConfig, final IGroupReplacements groupReplacements, final IClientReplacements clientReplacements, final IGenericMessageReplacements genericMessageReplacements, final IClientRankReplacements clientRankReplacements, final IClientUserReplacements clientUserReplacements)
    {
        this.genericMessageFormattingConfig = genericMessageFormattingConfig;
        this.groupReplacements = groupReplacements;
        this.clientReplacements = clientReplacements;
        this.genericMessageReplacements = genericMessageReplacements;
        this.clientRankReplacements = clientRankReplacements;
        this.clientUserReplacements = clientUserReplacements;
    }

    @Override
    public String format(final GenericMessageMessage message)
    {
        return FormattingUtils.format(genericMessageFormattingConfig.getGenericMessageFormattingString(message), replacementString -> childReplacements(replacementString, message));
    }

    private String childReplacements(final String replacementString, GenericMessageMessage message)
    {
        String returnString = groupReplacements.getReplacementObject(message.getGroup(), replacementString);
        if (returnString != null)
            return returnString;

        returnString = clientReplacements.getReplacementObject(message.getSendingClient(), replacementString);
        if (returnString != null)
            return returnString;

        returnString = genericMessageReplacements.getReplacementObject(message, replacementString);
        if (returnString != null)
            return returnString;

        if (message.getClientUser() != null)
        {
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

        return null;
    }
}
