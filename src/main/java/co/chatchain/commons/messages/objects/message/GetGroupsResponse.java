package co.chatchain.commons.messages.objects.message;

import co.chatchain.commons.messages.interfaces.IGroup;
import co.chatchain.commons.messages.interfaces.message.IGetGroupsResponse;

import java.util.List;

public class GetGroupsResponse<T1 extends IGroup> implements IGetGroupsResponse<T1>
{
    private List<T1> group;

    @Override
    public List<T1> getGroups()
    {
        return this.group;
    }
}
