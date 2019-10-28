package co.chatchain.commons.objects.requests;

import co.chatchain.commons.objects.ClientUser;

public class GenericMessageRequest
{

    private String groupId;
    private ClientUser clientUser;
    private String message;

    public GenericMessageRequest(final String groupId, final ClientUser clientUser, final String message)
    {
        this.groupId = groupId;
        this.clientUser = clientUser;
        this.message = message;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(final String groupId)
    {
        this.groupId = groupId;
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
