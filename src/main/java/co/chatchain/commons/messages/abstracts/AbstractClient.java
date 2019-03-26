package co.chatchain.commons.messages.abstracts;

public abstract class AbstractClient
{

    protected String clientId;
    protected String ownerId;
    protected String clientGuid;
    protected String clientName;

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(final String clientId)
    {
        this.clientId = clientId;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(final String ownerId)
    {
        this.ownerId = ownerId;
    }

    public String getClientGuid()
    {
        return clientGuid;
    }

    public void setClientGuid(final String clientGuid)
    {
        this.clientGuid = clientGuid;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(final String clientName)
    {
        this.clientName = clientName;
    }

}
