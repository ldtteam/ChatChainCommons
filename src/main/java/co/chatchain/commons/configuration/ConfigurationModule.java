package co.chatchain.commons.configuration;

import co.chatchain.commons.infrastructure.interfaces.configuration.IClientEventFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.IGenericMessageFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.IUserEventFormattingConfig;
import com.google.inject.AbstractModule;

public class ConfigurationModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(IClientEventFormattingConfig.class).to(FormattingConfig.class);
        bind(IGenericMessageFormattingConfig.class).to(FormattingConfig.class);
        bind(IUserEventFormattingConfig.class).to(FormattingConfig.class);
    }
}
