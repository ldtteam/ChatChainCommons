package co.chatchain.commons.infrastructure.replacements;

import co.chatchain.commons.core.entities.messages.GenericMessageMessage;
import co.chatchain.commons.infrastructure.interfaces.replacements.IGenericMessageReplacements;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("SameParameterValue")
public enum GenericMessageReplacements
{
    MESSAGE("message", GenericMessageMessage::getMessage);

    private final String replacement;
    private final GenericMessageReplacementAction action;

    GenericMessageReplacements(final String replacement, final GenericMessageReplacementAction action)
    {
        this.replacement = replacement;
        this.action = action;
    }

    @Nullable
    private String GetReplacementObject(final GenericMessageMessage genericMessage)
    {
        if (genericMessage == null)
            return null;

        return this.action.invoke(genericMessage);
    }

    private String getReplacement()
    {
        return replacement;
    }

    public GenericMessageReplacementAction getAction()
    {
        return action;
    }

    @Nullable
    private static GenericMessageReplacements GetFromReplacement(final String replacementString)
    {
        for (final GenericMessageReplacements genericMessageReplacement : values())
        {
            if (genericMessageReplacement.getReplacement().equalsIgnoreCase(replacementString))
            {
                return genericMessageReplacement;
            }
        }
        return null;
    }

    public static class GenericMessageReplacementsInstance implements IGenericMessageReplacements
    {
        @Nullable
        @Override
        public String getReplacementObject(final GenericMessageMessage genericMessage, final String replacementString)
        {
            final GenericMessageReplacements genericMessageReplacement = GetFromReplacement(replacementString);

            return genericMessageReplacement == null ? null : genericMessageReplacement.GetReplacementObject(genericMessage);
        }
    }

    public interface GenericMessageReplacementAction
    {
        String invoke(final GenericMessageMessage genericMessage);
    }
}
