package co.chatchain.commons.messages.objects;

import co.chatchain.commons.messages.interfaces.IGroup;

public class Group implements IGroup
{

    private String groupName;
    private String groupId;
    private String ownerId;

    public Group()
    {
    }

    public Group(final String groupId)
    {
        this.groupId = groupId;
    }

    public Group(final String groupId, final String groupName, final String ownerId)
    {
        this.groupId = groupId;
        this.ownerId = ownerId;
        this.groupName = groupName;
    }

    @Override
    public String getGroupName()
    {
        return groupName;
    }

    @Override
    public String getGroupId()
    {
        return groupId;
    }

    @Override
    public String getOwnerId()
    {
        return ownerId;
    }
}
