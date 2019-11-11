package co.chatchain.commons.infrastructure.replacements;

import co.chatchain.commons.core.entities.Client;
import co.chatchain.commons.infrastructure.interfaces.replacements.IClientReplacements;
import org.jetbrains.annotations.Nullable;

public enum ClientReplacements
{
    NAME("client-name", Client::getName),
    ID("client-id", Client::getId),
    DESCRIPTION("client-description", Client::getDescription),
    OWNER_ID("client-owner-id", Client::getOwnerId);

    private final String replacement;
    private final ClientReplacementAction action;

    ClientReplacements(final String replacement, final ClientReplacementAction action)
    {
        this.replacement = replacement;
        this.action = action;
    }

    @Nullable
    private String GetReplacementObject(final Client client)
    {
        if (client == null)
            return null;

        return this.action.invoke(client);
    }

    private String getReplacement()
    {
        return replacement;
    }

    public ClientReplacementAction getAction()
    {
        return action;
    }

    @Nullable
    private static ClientReplacements GetFromReplacement(final String replacementString)
    {
        for (final ClientReplacements clientReplacement : values())
        {
            if (clientReplacement.getReplacement().equalsIgnoreCase(replacementString))
            {
                return clientReplacement;
            }
        }
        return null;
    }

    public static class ClientReplacementsInstance implements IClientReplacements
    {
        @Nullable
        @Override
        public String getReplacementObject(final Client client, final String replacementString)
        {
            final ClientReplacements clientReplacement = GetFromReplacement(replacementString);

            return clientReplacement == null ? null : clientReplacement.GetReplacementObject(client);
        }
    }

    public interface ClientReplacementAction
    {
        String invoke(final Client client);
    }
}
