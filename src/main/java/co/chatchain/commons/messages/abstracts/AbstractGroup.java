package co.chatchain.commons.messages.abstracts;

public abstract class AbstractGroup
{

    protected String groupId;
    protected String groupName;
    protected String ownerId;

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(final String groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(final String groupName)
    {
        this.groupName = groupName;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(final String ownerId)
    {
        this.ownerId = ownerId;
    }

}
