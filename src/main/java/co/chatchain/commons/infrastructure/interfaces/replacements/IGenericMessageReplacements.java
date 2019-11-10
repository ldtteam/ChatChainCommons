package co.chatchain.commons.infrastructure.interfaces.replacements;

import co.chatchain.commons.core.entites.messages.GenericMessageMessage;
import org.jetbrains.annotations.Nullable;

public interface IGenericMessageReplacements
{
    @Nullable
    String getReplacementObject(final GenericMessageMessage genericMessage, final String replacementString);
}
