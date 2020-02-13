package co.chatchain.commons.configuration;

import co.chatchain.commons.infrastructure.interfaces.configuration.events.IClientEventFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.IFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.IGenericMessageFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.events.IUserEventFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.stats.IStatsResponseFormattingConfig;
import com.google.inject.AbstractModule;

import java.io.File;
import java.io.IOException;

public class ConfigurationModule extends AbstractModule
{

    private final File formattingConfigPath;
    private final boolean advanced;

    public ConfigurationModule(final File formattingConfigPath, final boolean advanced)
    {
        this.formattingConfigPath = formattingConfigPath;
        this.advanced = advanced;
    }

    @Override
    protected void configure()
    {
        final IFormattingConfig formattingConfig = new FormattingConfig(this.advanced);
        try
        {
            formattingConfig.load(this.formattingConfigPath);
        }
        catch (final IOException e)
        {
            System.out.println("Failed to load Formatting Config with: ");
            e.printStackTrace();
        }

        bind(IClientEventFormattingConfig.class).toInstance(formattingConfig);
        bind(IGenericMessageFormattingConfig.class).toInstance(formattingConfig);
        bind(IUserEventFormattingConfig.class).toInstance(formattingConfig);
        bind(IStatsResponseFormattingConfig.class).toInstance(formattingConfig);
        bind(IFormattingConfig.class).toInstance(formattingConfig);
    }
}
