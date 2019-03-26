package co.chatchain.commons.messages.abstracts.messages;

import co.chatchain.commons.messages.objects.Group;

import java.util.List;

public abstract class AbstractGetGroupsResponse<T1 extends Group>
{
    protected List<T1> groups;

    public List<T1> getGroups()
    {
        return this.groups;
    }

    public void setGroups(final List<T1> groups)
    {
        this.groups = groups;
    }

    public void addToGroups(final T1 group)
    {
        this.groups.add(group);
    }
}
