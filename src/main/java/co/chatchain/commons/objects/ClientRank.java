package co.chatchain.commons.objects;

public class ClientRank
{

    private String name;
    private String uniqueId;
    private int priority;
    private String display;
    private String colour;

    public ClientRank()
    {
    }

    public ClientRank(final String name, final String uniqueId, final int priority, final String display, final String colour)
    {
        this.name = name;
        this.uniqueId = uniqueId;
        this.priority = priority;
        this.display = display;
        this.colour = colour;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getUniqueId()
    {
        return uniqueId;
    }

    public void setUniqueId(final String uniqueId)
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

    public String getDisplay()
    {
        return display;
    }

    public void setDisplay(final String display)
    {
        this.display = display;
    }

    public String getColour()
    {
        return colour;
    }

    public void setColour(final String colour)
    {
        this.colour = colour;
    }
}
