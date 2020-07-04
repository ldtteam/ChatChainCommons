package co.chatchain.commons.infrastructure.interfaces.replacements;

import co.chatchain.commons.core.entities.StatsObject;
import org.jetbrains.annotations.Nullable;

public interface IStatsObjectReplacements
{
    @Nullable
    String getReplacementObject(final StatsObject statsObject, final String replacementString);
}
