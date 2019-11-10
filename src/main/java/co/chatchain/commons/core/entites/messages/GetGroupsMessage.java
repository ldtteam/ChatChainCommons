package co.chatchain.commons.core.entites.messages;

import co.chatchain.commons.core.entites.Group;
import co.chatchain.commons.core.interfaces.IMessage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetGroupsMessage implements IMessage
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
