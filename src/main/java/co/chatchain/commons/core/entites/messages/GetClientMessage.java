package co.chatchain.commons.core.entites.messages;

import co.chatchain.commons.core.entites.Client;
import co.chatchain.commons.core.interfaces.IMessage;
import org.jetbrains.annotations.NotNull;

public class GetClientMessage implements IMessage
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
