package co.chatchain.commons.configuration;

import co.chatchain.commons.infrastructure.interfaces.configuration.IClientEventFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.IFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.IGenericMessageFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.IUserEventFormattingConfig;
import com.google.inject.AbstractModule;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;

import java.nio.file.Path;

public class ConfigurationModule extends AbstractModule
{

    private final Path formattingConfigPath;
    private final boolean advanced;

    public ConfigurationModule(final Path formattingConfigPath, final boolean advanced)
    {
        this.formattingConfigPath = formattingConfigPath;
        this.advanced = advanced;
    }

    @Override
    protected void configure()
    {
        IFormattingConfig formattingConfig;
        if (advanced)
        {
            formattingConfig = AbstractConfig.getConfig(formattingConfigPath, AdvancedFormattingConfig.class,
                    GsonConfigurationLoader.builder().setPath(formattingConfigPath).build());
        }
        else
        {
            formattingConfig = AbstractConfig.getConfig(formattingConfigPath, FormattingConfig.class,
                    GsonConfigurationLoader.builder().setPath(formattingConfigPath).build());
        }

        bind(IClientEventFormattingConfig.class).toInstance(formattingConfig);
        bind(IGenericMessageFormattingConfig.class).toInstance(formattingConfig);
        bind(IUserEventFormattingConfig.class).toInstance(formattingConfig);
    }
}
