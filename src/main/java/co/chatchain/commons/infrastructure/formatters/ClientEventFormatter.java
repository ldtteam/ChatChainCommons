package co.chatchain.commons.infrastructure.formatters;

import co.chatchain.commons.core.entities.messages.ClientEventMessage;
import co.chatchain.commons.core.interfaces.formatters.IClientEventFormatter;
import co.chatchain.commons.infrastructure.interfaces.configuration.IClientEventFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.replacements.IClientReplacements;
import co.chatchain.commons.infrastructure.interfaces.replacements.IGroupReplacements;
import co.chatchain.commons.infrastructure.utils.FormattingUtils;

import javax.inject.Inject;

public class ClientEventFormatter implements IClientEventFormatter
{
    private final IClientEventFormattingConfig clientEventFormattingConfig;
    private final IGroupReplacements groupReplacements;
    private final IClientReplacements clientReplacements;

    @Inject
    public ClientEventFormatter(final IClientEventFormattingConfig clientEventFormattingConfig, final IGroupReplacements groupReplacements, final IClientReplacements clientReplacements)
    {
        this.clientEventFormattingConfig = clientEventFormattingConfig;
        this.groupReplacements = groupReplacements;
        this.clientReplacements = clientReplacements;
    }

    @Override
    public String format(final ClientEventMessage message)
    {
        return FormattingUtils.format(clientEventFormattingConfig.getClientEventFormattingString(message), replacementString -> childReplacements(replacementString, message));
    }

    private String childReplacements(final String replacementString, ClientEventMessage message)
    {
        String returnString = groupReplacements.getReplacementObject(message.getGroup(), replacementString);
        if (returnString != null)
            return returnString;

        returnString = clientReplacements.getReplacementObject(message.getSendingClient(), replacementString);
        return returnString;
    }
}
