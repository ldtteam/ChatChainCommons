package co.chatchain.commons.infrastructure.interfaces.replacements;

import co.chatchain.commons.core.entites.Client;
import org.jetbrains.annotations.Nullable;

public interface IClientReplacements
{
    @Nullable
    String getReplacementObject(final Client client, final String replacementString);
}
