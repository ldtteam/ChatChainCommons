package co.chatchain.commons.objects;

public class Client
{

    private String id;
    private String ownerId;
    private String name;
    private String description;

    public Client()
    {
    }

    public Client(final String id, final String ownerId, final String name, final String description)
    {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(final String ownerId)
    {
        this.ownerId = ownerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }
}
