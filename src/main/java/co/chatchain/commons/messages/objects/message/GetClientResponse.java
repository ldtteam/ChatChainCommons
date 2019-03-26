package co.chatchain.commons.messages.objects.message;

import co.chatchain.commons.messages.interfaces.message.IGetClientResponse;
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
