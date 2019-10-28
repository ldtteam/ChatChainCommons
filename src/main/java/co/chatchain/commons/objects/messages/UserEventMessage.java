package co.chatchain.commons.objects.messages;

import co.chatchain.commons.objects.Client;
import co.chatchain.commons.objects.ClientUser;
import co.chatchain.commons.objects.Group;

import java.util.Dictionary;
import java.util.List;

public class UserEventMessage
{

    private Client sendingClient;
    private ClientUser clientUser;
    private String clientId;
    private Group group;
    private List<Group> groups;
    private String event;
    private Dictionary<String, String> EventData;

    public UserEventMessage()
    {
    }

    public UserEventMessage(final Client sendingClient, final ClientUser clientUser, final String clientId, final Group group, final List<Group> groups, final String event, final Dictionary<String, String> eventData)
    {
        this.sendingClient = sendingClient;
        this.clientUser = clientUser;
        this.clientId = clientId;
        this.group = group;
        this.groups = groups;
        this.event = event;
        EventData = eventData;
    }

    public Client getSendingClient()
    {
        return sendingClient;
    }

    public void setSendingClient(final Client sendingClient)
    {
        this.sendingClient = sendingClient;
    }

    public ClientUser getClientUser()
    {
        return clientUser;
    }

    public void setClientUser(final ClientUser clientUser)
    {
        this.clientUser = clientUser;
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

    public List<Group> getGroups()
    {
        return groups;
    }

    public void setGroups(final List<Group> groups)
    {
        this.groups = groups;
    }

    public String getEvent()
    {
        return event;
    }

    public void setEvent(final String event)
    {
        this.event = event;
    }

    public Dictionary<String, String> getEventData()
    {
        return EventData;
    }

    public void setEventData(final Dictionary<String, String> eventData)
    {
        EventData = eventData;
    }
}
