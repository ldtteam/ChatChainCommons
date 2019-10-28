package co.chatchain.commons.objects.messages;

import co.chatchain.commons.objects.Client;

public class GetClientMessage
{
    private Client client;

    public GetClientMessage()
    {
    }

    public GetClientMessage(final Client client)
    {
        this.client = client;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(final Client client)
    {
        this.client = client;
    }
}
