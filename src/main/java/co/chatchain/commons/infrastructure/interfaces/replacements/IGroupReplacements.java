package co.chatchain.commons.infrastructure.interfaces.replacements;

import co.chatchain.commons.core.entites.Group;
import org.jetbrains.annotations.Nullable;

public interface IGroupReplacements
{
    @Nullable
    String getReplacementObject(final Group group, final String replacementString);
}
