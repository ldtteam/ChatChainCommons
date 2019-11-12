package co.chatchain.commons.core.entities;

import org.jetbrains.annotations.Nullable;

public class ClientRank
{

    @Nullable
    private String name;
    @Nullable
    private String uniqueId;
    private int priority;
    @Nullable
    private String display;
    @Nullable
    private String colour;

    public ClientRank()
    {
    }

    public ClientRank(@Nullable final String name, @Nullable final String uniqueId, final int priority, @Nullable final String display, @Nullable final String colour)
    {
        this.name = name;
        this.uniqueId = uniqueId;
        this.priority = priority;
        this.display = display;
        this.colour = colour;
    }

    @Nullable
    public String getName()
    {
        return name;
    }

    public void setName(@Nullable final String name)
    {
        this.name = name;
    }

    @Nullable
    public String getUniqueId()
    {
        return uniqueId;
    }

    public void setUniqueId(@Nullable final String uniqueId)
    {
        this.uniqueId = uniqueId;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(final int priority)
    {
        this.priority = priority;
    }

    @Nullable
    public String getDisplay()
    {
        return display;
    }

    public void setDisplay(@Nullable final String display)
    {
        this.display = display;
    }

    @Nullable
    public String getColour()
    {
        return colour;
    }

    public void setColour(@Nullable final String colour)
    {
        this.colour = colour;
    }
}
