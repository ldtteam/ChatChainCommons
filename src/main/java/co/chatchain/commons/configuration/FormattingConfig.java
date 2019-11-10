package co.chatchain.commons.configuration;

import co.chatchain.commons.core.entites.messages.ClientEventMessage;
import co.chatchain.commons.core.entites.messages.GenericMessageMessage;
import co.chatchain.commons.core.entites.messages.UserEventMessage;
import co.chatchain.commons.infrastructure.interfaces.configuration.IClientEventFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.IGenericMessageFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.IUserEventFormattingConfig;

public class FormattingConfig implements IClientEventFormattingConfig, IGenericMessageFormattingConfig, IUserEventFormattingConfig
{
    @Override
    public String[] getClientEventFormattingString(final ClientEventMessage message)
    {
        return new String[] {"[{group-name}] ", "{client-name} has connected"};
    }

    @Override
    public String[] getGenericMessageFormattingString(final GenericMessageMessage message)
    {
        return new String[] {"[{client-name}] ", "[{client-rank-display}] ", "<{client-user-nickname||client-user-name}>: {message}"};
    }

    @Override
    public String[] getUserEventFormattingString(final UserEventMessage message)
    {
        return new String[] {"[{group-name}] ", "[{client-name}] ", "{client-user-nickname||client-user-name} has logged in"};
    }
}
