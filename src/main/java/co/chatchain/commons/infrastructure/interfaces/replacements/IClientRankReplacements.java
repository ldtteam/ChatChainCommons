package co.chatchain.commons.infrastructure.interfaces.replacements;

import co.chatchain.commons.core.entites.ClientRank;
import org.jetbrains.annotations.Nullable;

public interface IClientRankReplacements
{
    @Nullable
    String getReplacementObject(final ClientRank rank, final String replacementString);
}
