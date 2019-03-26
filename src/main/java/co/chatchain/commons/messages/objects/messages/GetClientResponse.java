package co.chatchain.commons.messages.objects.messages;

import co.chatchain.commons.messages.interfaces.messages.IGetClientResponse;
import co.chatchain.commons.messages.objects.Client;

public class GetClientResponse implements IGetClientResponse<Client>
{
    private Client client;

    @Override
    public Client getClient()
    {
        return this.client;
    }
}
