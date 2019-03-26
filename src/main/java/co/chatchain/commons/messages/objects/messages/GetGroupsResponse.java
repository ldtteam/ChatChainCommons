package co.chatchain.commons.messages.objects.messages;

import co.chatchain.commons.messages.interfaces.messages.IGetGroupsResponse;
import co.chatchain.commons.messages.objects.Group;

import java.util.List;

public class GetGroupsResponse implements IGetGroupsResponse<Group>
{
    private List<Group> groups;

    @Override
    public List<Group> getGroups()
    {
        return this.groups;
    }
}
