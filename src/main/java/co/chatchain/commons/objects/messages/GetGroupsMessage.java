package co.chatchain.commons.objects.messages;

import co.chatchain.commons.objects.Group;

import java.util.List;

public class GetGroupsMessage
{

    private List<Group> groups;

    public GetGroupsMessage()
    {
    }

    public GetGroupsMessage(final List<Group> groups)
    {
        this.groups = groups;
    }

    public List<Group> getGroups()
    {
        return groups;
    }

    public void setGroups(final List<Group> groups)
    {
        this.groups = groups;
    }
}
