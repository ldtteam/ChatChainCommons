package co.chatchain.commons.objects.messages;

import co.chatchain.commons.objects.Client;
import org.jetbrains.annotations.NotNull;

public class GetClientMessage
{
    @NotNull
    private Client client;

    public GetClientMessage()
    {
    }

    public GetClientMessage(@NotNull final Client client)
    {
        this.client = client;
    }

    @NotNull
    public Client getClient()
    {
        return client;
    }

    public void setClient(@NotNull final Client client)
    {
        this.client = client;
    }
}
