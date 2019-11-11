package co.chatchain.commons.core.entities.messages;

import co.chatchain.commons.core.entities.Group;
import co.chatchain.commons.core.interfaces.IMessage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("NullableProblems")
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
