package co.chatchain.commons.messages.objects.messages;

import co.chatchain.commons.messages.abstracts.messages.AbstractGetGroupsResponse;
import co.chatchain.commons.messages.objects.Group;

import java.util.List;

public class GetGroupsResponse extends AbstractGetGroupsResponse<Group>
{
    private List<Group> groups;

    @Override
    public List<Group> getGroups()
    {
        return this.groups;
    }
}
