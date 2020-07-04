package co.chatchain.commons.infrastructure.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormattingUtils
{
    private static final Pattern MOTHER_REPLACEMENT = Pattern.compile("\\{.*?}");
    @SuppressWarnings("Annotator")
    private static final Pattern OR_REPLACEMENT = Pattern.compile("[^|]*");

    /**
     * In this implementation, we grab a formatting string array from a formatting config.
     * Each Array entry is optional, if a value within is null the array entry is skipped (useful if not every client returns a value).
     * Within each entry there are Mother and Child replacements, a mother replacement are two "{}" surrounding a child replacement.
     * A child replacement is a list of possible replacements, in the format of: "one||two||three", the first replacement that isn't null is used.
     * if all child replacements are null, the mother replacement is null, and that array entry is then skipped.
     *
     * A full example formatting array would be:
     * 1: "[{group-name}] "
     * 2: "{client-name||client-id} has connected"
     * In this example, if group-name is null, the entry is skipped.
     * If client-name is null then client-id is used. and if both are null the entry is skipped.
     * After the replacements are over all array entries are concatenated together.
     *
     * @param format Formatting string to use
     * @return Formatted message returned
     */
    public static String format(final List<String> format, final Action childReplacementsAction)
    {
        final StringBuilder outputStringBuilder = new StringBuilder();

        for (final String formatSection : format)
        {
            final String[] replacementSections = formatSection.split(MOTHER_REPLACEMENT.pattern());
            final Matcher motherMatcher = MOTHER_REPLACEMENT.matcher(formatSection);
            final StringBuilder motherStringBuilder = new StringBuilder();

            if (replacementSections.length > 0)
            {
                motherStringBuilder.append(replacementSections[0]);
            }

            int index = 0;
            boolean skip = false;

            while (motherMatcher.find())
            {
                index++;
                final String motherString = motherMatcher.group().replace("{", "").replace("}", "");

                final String replacementString = getChildReplacement(motherString, childReplacementsAction);
                if (replacementString == null)
                {
                    skip = true;
                    break;
                }

                motherStringBuilder.append(replacementString);
                if (replacementSections.length > index)
                {
                    motherStringBuilder.append(replacementSections[index]);
                }
            }

            if (!skip)
            {
                outputStringBuilder.append(motherStringBuilder.toString());
            }
        }

        return outputStringBuilder.toString();
    }

    private static String getChildReplacement(final String motherString, final Action childReplacementsAction)
    {
        final Matcher childMatcher = OR_REPLACEMENT.matcher(motherString);

        while (childMatcher.find())
        {
            final String matchString = childMatcher.group();

            if (!matchString.equals(""))
            {
                final String replacement = childReplacementsAction.invoke(matchString);
                if (replacement != null)
                {
                    return replacement;
                }
            }
        }

        return null;
    }

    public interface Action
    {
        String invoke(final String string);
    }
}
