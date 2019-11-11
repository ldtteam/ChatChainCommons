package co.chatchain.commons.infrastructure.replacements;

import co.chatchain.commons.core.entities.ClientUser;
import co.chatchain.commons.infrastructure.interfaces.replacements.IClientUserReplacements;
import org.jetbrains.annotations.Nullable;

public enum ClientUserReplacements
{
    NAME("client-user-name", ClientUser::getName),
    UID("client-user-uid", ClientUser::getUniqueId),
    NICKNAME("client-user-nickname", ClientUser::getNickName),
    COLOUR("client-user-colour", ClientUser::getColour);

    private final String replacement;
    private final UserReplacementAction action;

    ClientUserReplacements(final String replacement, final UserReplacementAction action)
    {
        this.replacement = replacement;
        this.action = action;
    }

    @Nullable
    private String GetReplacementObject(final ClientUser user)
    {
        if (user == null)
            return null;

        return this.action.invoke(user);
    }

    private String getReplacement()
    {
        return replacement;
    }

    public UserReplacementAction getAction()
    {
        return action;
    }

    @Nullable
    private static ClientUserReplacements GetFromReplacement(final String replacementString)
    {
        for (final ClientUserReplacements userReplacement : values())
        {
            if (userReplacement.getReplacement().equalsIgnoreCase(replacementString))
            {
                return userReplacement;
            }
        }
        return null;
    }

    public static class ClientUserReplacementsInstance implements IClientUserReplacements
    {
        @Nullable
        @Override
        public String getReplacementObject(final ClientUser user, final String replacementString)
        {
            final ClientUserReplacements userReplacement = GetFromReplacement(replacementString);

            return userReplacement == null ? null : userReplacement.GetReplacementObject(user);
        }
    }

    public interface UserReplacementAction
    {
        String invoke(final ClientUser user);
    }
}
