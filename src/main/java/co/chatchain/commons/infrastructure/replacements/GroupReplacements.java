package co.chatchain.commons.infrastructure.replacements;

import co.chatchain.commons.core.entites.Group;
import co.chatchain.commons.infrastructure.interfaces.replacements.IGroupReplacements;
import org.jetbrains.annotations.Nullable;

public enum GroupReplacements
{
    NAME("group-name", Group::getName),
    ID("group-id", Group::getId),
    DESCRIPTION("group-description", Group::getDescription),
    OWNER_ID("group-owner-id", Group::getOwnerId);

    final String replacement;
    final GroupReplacementAction action;

    GroupReplacements(final String replacement, final GroupReplacementAction action)
    {
        this.replacement = replacement;
        this.action = action;
    }

    @Nullable
    public String GetReplacementObject(final Group group)
    {
        if (group == null)
            return null;

        return this.action.invoke(group);
    }

    public String getReplacement()
    {
        return replacement;
    }

    public GroupReplacementAction getAction()
    {
        return action;
    }

    @Nullable
    public static GroupReplacements GetFromReplacement(final String replacementString)
    {
        for (final GroupReplacements groupReplacement : values())
        {
            if (groupReplacement.getReplacement().equalsIgnoreCase(replacementString))
            {
                return groupReplacement;
            }
        }
        return null;
    }

    public static class GroupReplacementsInstance implements IGroupReplacements
    {
        @Nullable
        @Override
        public String getReplacementObject(final Group group, final String replacementString)
        {
            final GroupReplacements groupReplacement = GetFromReplacement(replacementString);

            return groupReplacement == null ? null : groupReplacement.GetReplacementObject(group);
        }
    }

    public interface GroupReplacementAction
    {
        String invoke(final Group group);
    }
}
