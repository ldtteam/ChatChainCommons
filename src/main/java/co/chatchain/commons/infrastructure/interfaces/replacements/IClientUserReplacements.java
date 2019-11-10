package co.chatchain.commons.infrastructure.interfaces.replacements;

import co.chatchain.commons.core.entites.ClientUser;
import org.jetbrains.annotations.Nullable;

public interface IClientUserReplacements
{
    @Nullable
    String getReplacementObject(final ClientUser user, final String replacementString);
}
