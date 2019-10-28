package co.chatchain.commons.objects.messages;

import co.chatchain.commons.objects.Client;
import co.chatchain.commons.objects.ClientUser;
import co.chatchain.commons.objects.Group;

public class GenericMessageMessage
{

    private Client sendingClient;
    private String clientId;
    private Group group;
    private ClientUser clientUser;
    private String message;

    public GenericMessageMessage()
    {
    }

    public GenericMessageMessage(final Client sendingClient, final String clientId, final Group group, final ClientUser clientUser, final String message)
    {
        this.sendingClient = sendingClient;
        this.clientId = clientId;
        this.group = group;
        this.clientUser = clientUser;
        this.message = message;
    }

    public Client getSendingClient()
    {
        return sendingClient;
    }

    public void setSendingClient(final Client sendingClient)
    {
        this.sendingClient = sendingClient;
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(final String clientId)
    {
        this.clientId = clientId;
    }

    public Group getGroup()
    {
        return group;
    }

    public void setGroup(final Group group)
    {
        this.group = group;
    }

    public ClientUser getClientUser()
    {
        return clientUser;
    }

    public void setClientUser(final ClientUser clientUser)
    {
        this.clientUser = clientUser;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(final String message)
    {
        this.message = message;
    }
}
