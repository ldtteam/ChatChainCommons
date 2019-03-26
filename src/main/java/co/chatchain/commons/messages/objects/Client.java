package co.chatchain.commons.messages.objects;

import co.chatchain.commons.messages.interfaces.IClient;

public class Client implements IClient
{

    private String clientId;
    private String ownerId;
    private String clientGuid;
    private String clientName;

    public Client()
    {
    }

    @Override
    public String getClientId()
    {
        return clientId;
    }

    @Override
    public String getOwnerId()
    {
        return ownerId;
    }

    @Override
    public String getClientGuid()
    {
        return clientGuid;
    }

    @Override
    public String getClientName()
    {
        return clientName;
    }
}
