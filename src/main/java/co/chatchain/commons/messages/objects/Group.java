package co.chatchain.commons.messages.objects;

import co.chatchain.commons.messages.abstracts.AbstractGroup;

public class Group extends AbstractGroup
{

    public Group(final String groupId)
    {
        this(groupId, null, null);
    }

    public Group(final String groupId, final String groupName, final String ownerId)
    {
        this.groupId = groupId;
        this.groupName = groupName;
        this.ownerId = ownerId;
    }
}
