package co.chatchain.commons.configuration;

import co.chatchain.commons.configuration.formats.MessageFormats;

import java.util.List;

public interface FormatAction
{
    List<String> invoke(final MessageFormats formats);
}
