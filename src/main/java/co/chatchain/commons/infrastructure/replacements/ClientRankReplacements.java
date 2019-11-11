package co.chatchain.commons.infrastructure.replacements;

import co.chatchain.commons.core.entities.ClientRank;
import co.chatchain.commons.infrastructure.interfaces.replacements.IClientRankReplacements;
import org.jetbrains.annotations.Nullable;

public enum ClientRankReplacements
{
    NAME("client-rank-name", ClientRank::getName),
    UID("client-rank-uid", ClientRank::getUniqueId),
    PRIORITY("client-rank-priority", rank -> String.valueOf(rank.getPriority())),
    DISPLAY("client-rank-display", ClientRank::getDisplay),
    COLOUR("client-rank-colour", ClientRank::getColour);

    private final String replacement;
    private final ClientRankReplacementAction action;

    ClientRankReplacements(final String replacement, final ClientRankReplacementAction action)
    {
        this.replacement = replacement;
        this.action = action;
    }

    @Nullable
    private String GetReplacementObject(final ClientRank rank)
    {
        if (rank == null)
            return null;

        return this.action.invoke(rank);
    }

    private String getReplacement()
    {
        return replacement;
    }

    public ClientRankReplacementAction getAction()
    {
        return action;
    }

    @Nullable
    private static ClientRankReplacements GetFromReplacement(final String replacementString)
    {
        for (final ClientRankReplacements rankReplacement : values())
        {
            if (rankReplacement.getReplacement().equalsIgnoreCase(replacementString))
            {
                return rankReplacement;
            }
        }
        return null;
    }

    public static class ClientRankReplacementsInstance implements IClientRankReplacements
    {
        @Nullable
        @Override
        public String getReplacementObject(final ClientRank rank, final String replacementString)
        {
            final ClientRankReplacements rankReplacement = GetFromReplacement(replacementString);

            return rankReplacement == null ? null : rankReplacement.GetReplacementObject(rank);
        }
    }

    public interface ClientRankReplacementAction
    {
        String invoke(final ClientRank rank);
    }
}
