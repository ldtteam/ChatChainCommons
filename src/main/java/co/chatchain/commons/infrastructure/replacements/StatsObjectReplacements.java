package co.chatchain.commons.infrastructure.replacements;

import co.chatchain.commons.core.entities.ClientUser;
import co.chatchain.commons.core.entities.StatsObject;
import co.chatchain.commons.infrastructure.interfaces.replacements.IStatsObjectReplacements;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public enum StatsObjectReplacements
{
    ONLINE_USERS("online-users", statsObject -> statsObject.getOnlineUsers() == null ? null : Arrays.toString(statsObject.getOnlineUsers().stream().map(ClientUser::getName).toArray())),
    PERFORMANCE("performance", StatsObject::getPerformance),
    PERFORMANCE_NAME("performance-name", StatsObject::getPerformanceName);

    private final String replacement;
    private final StatsResponseMessageReplacementAction action;

    StatsObjectReplacements(final String replacement, final StatsResponseMessageReplacementAction action)
    {
        this.replacement = replacement;
        this.action = action;
    }

    @Nullable
    private String getReplacementObject(final StatsObject statsObject)
    {
        if (statsObject == null)
            return null;

        return this.action.invoke(statsObject);
    }

    public String getReplacement()
    {
        return replacement;
    }

    public StatsResponseMessageReplacementAction getAction()
    {
        return action;
    }

    @Nullable
    private static StatsObjectReplacements GetFromReplacement(final String replacementString)
    {
        for (final StatsObjectReplacements statsObjectReplacement : values())
        {
            if (statsObjectReplacement.getReplacement().equalsIgnoreCase(replacementString))
            {
                return statsObjectReplacement;
            }
        }
        return null;
    }

    public static class StatsObjectReplacementsInstance implements IStatsObjectReplacements
    {
        @Nullable
        @Override
        public String getReplacementObject(final StatsObject statsObject, final String replacementString)
        {
            final StatsObjectReplacements statsObjectReplacement = GetFromReplacement(replacementString);

            return statsObjectReplacement == null ? null : statsObjectReplacement.getReplacementObject(statsObject);
        }
    }

    public interface StatsResponseMessageReplacementAction
    {
        String invoke(final StatsObject statsObject);
    }
}
