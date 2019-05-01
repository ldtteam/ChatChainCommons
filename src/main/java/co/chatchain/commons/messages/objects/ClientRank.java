package co.chatchain.commons.messages.objects;

import co.chatchain.commons.messages.interfaces.IClientRank;

public class ClientRank implements IClientRank
{

    private String name;
    private String uniqueId;
    private int priority;
    private String display;
    private String colour;

    public ClientRank(final String name, final String uniqueId, final int priority, final String display, final String colour)
    {
        this.name = name;
        this.uniqueId = uniqueId;
        this.priority = priority;
        this.display = display;
        this.colour = colour;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public String getUniqueId()
    {
        return this.uniqueId;
    }

    @Override
    public int getPriority()
    {
        return this.priority;
    }

    @Override
    public String getDisplay()
    {
        return this.display;
    }

    @Override
    public String getColour()
    {
        return this.colour;
    }
}
