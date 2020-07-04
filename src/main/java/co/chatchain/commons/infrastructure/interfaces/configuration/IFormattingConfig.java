package co.chatchain.commons.infrastructure.interfaces.configuration;

import co.chatchain.commons.infrastructure.interfaces.configuration.events.IClientEventFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.events.IUserEventFormattingConfig;
import co.chatchain.commons.infrastructure.interfaces.configuration.stats.IStatsResponseFormattingConfig;

import java.io.File;
import java.io.IOException;

public interface IFormattingConfig extends IClientEventFormattingConfig, IGenericMessageFormattingConfig, IUserEventFormattingConfig, IStatsResponseFormattingConfig
{

    void save(final File saveFile) throws IOException;

    void load(final File loadFile) throws IOException;

}
