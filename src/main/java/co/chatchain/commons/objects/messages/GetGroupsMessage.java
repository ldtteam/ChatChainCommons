package co.chatchain.commons.objects.messages;

import co.chatchain.commons.objects.Group;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetGroupsMessage
{
    @NotNull
    private List<Group> groups;

    public GetGroupsMessage()
    {
    }

    public GetGroupsMessage(@NotNull final List<Group> groups)
    {
        this.groups = groups;
    }

    @NotNull
    public List<Group> getGroups()
    {
        return groups;
    }

    public void setGroups(@NotNull final List<Group> groups)
    {
        this.groups = groups;
    }
}
