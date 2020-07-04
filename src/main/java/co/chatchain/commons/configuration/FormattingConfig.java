package co.chatchain.commons.configuration;

import co.chatchain.commons.configuration.formats.IMessageFormats;
import co.chatchain.commons.configuration.formats.MessageFormatsRoot;
import co.chatchain.commons.core.entities.messages.GenericMessageMessage;
import co.chatchain.commons.core.entities.messages.events.ClientEventMessage;
import co.chatchain.commons.core.entities.messages.events.UserEventMessage;
import co.chatchain.commons.core.entities.messages.stats.StatsResponseMessage;
import co.chatchain.commons.infrastructure.interfaces.configuration.IFormattingConfig;
import com.google.gson.*;

import java.io.*;
import java.util.List;

public class FormattingConfig implements IFormattingConfig
{
    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final IMessageFormats formats;

    public FormattingConfig(final boolean advanced)
    {
        formats = new MessageFormatsRoot(advanced);
    }

    @Override
    public void save(final File saveFile) throws IOException
    {
        try (final Writer writer = new FileWriter(saveFile))
        {
            GSON.toJson(formats.serialize(), writer);
        }
    }

    @Override
    public void load(final File loadFile) throws IOException
    {
        try (final Reader reader = new FileReader(loadFile))
        {
            formats.deserialize(new JsonParser().parse(reader));

        }
    }

    @Override
    public List<String> getGenericMessageFormattingString(final GenericMessageMessage message)
    {
        return formats.getGenericMessage(message);
    }

    @Override
    public List<String> getClientEventFormattingString(final ClientEventMessage message)
    {
        return formats.getClientEventMessages(message);
    }

    @Override
    public List<String> getUserEventFormattingString(final UserEventMessage message)
    {
        return formats.getUserEventMessages(message);
    }

    @Override
    public List<String> getStatsResponseFormattingString(final StatsResponseMessage message)
    {
        return formats.getStatsResponses(message);
    }
}
