package co.chatchain.commons.core.entites.messages;

import co.chatchain.commons.core.entites.Client;
import co.chatchain.commons.core.entites.ClientUser;
import co.chatchain.commons.core.entites.Group;
import co.chatchain.commons.core.interfaces.IMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GenericMessageMessage implements IMessage
{

    @NotNull
    private Client sendingClient;
    @NotNull
    private String clientId;
    @NotNull
    private Group group;
    @NotNull
    private String message;
    @Nullable
    private ClientUser clientUser;

    public GenericMessageMessage()
    {
    }

    public GenericMessageMessage(@NotNull final Client sendingClient, @NotNull final String clientId, @NotNull final Group group, @NotNull final String message)
    {
        this.sendingClient = sendingClient;
        this.clientId = clientId;
        this.group = group;
        this.message = message;
    }

    public GenericMessageMessage(@NotNull final Client sendingClient, @NotNull final String clientId, @NotNull final Group group, @NotNull final String message, @Nullable final ClientUser clientUser)
    {
        this.sendingClient = sendingClient;
        this.clientId = clientId;
        this.group = group;
        this.message = message;
        this.clientUser = clientUser;
    }

    @NotNull
    public Client getSendingClient()
    {
        return sendingClient;
    }

    public void setSendingClient(@NotNull final Client sendingClient)
    {
        this.sendingClient = sendingClient;
    }

    @NotNull
    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(@NotNull final String clientId)
    {
        this.clientId = clientId;
    }

    @NotNull
    public Group getGroup()
    {
        return group;
    }

    public void setGroup(@NotNull final Group group)
    {
        this.group = group;
    }

    @NotNull
    public String getMessage()
    {
        return message;
    }

    public void setMessage(@NotNull final String message)
    {
        this.message = message;
    }

    @Nullable
    public ClientUser getClientUser()
    {
        return clientUser;
    }

    public void setClientUser(@Nullable final ClientUser clientUser)
    {
        this.clientUser = clientUser;
    }
}
