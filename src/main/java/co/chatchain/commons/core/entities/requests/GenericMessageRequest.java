package co.chatchain.commons.core.entities.requests;

import co.chatchain.commons.core.entities.ClientUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("NullableProblems")
public class GenericMessageRequest
{

    @NotNull
    private String groupId;
    @NotNull
    private String message;
    @Nullable
    private ClientUser clientUser;

    public GenericMessageRequest()
    {
    }

    public GenericMessageRequest(@NotNull final String groupId, @NotNull final String message)
    {
        this.groupId = groupId;
        this.message = message;
    }

    public GenericMessageRequest(@NotNull final String groupId, @NotNull final String message, @Nullable final ClientUser clientUser)
    {
        this.groupId = groupId;
        this.message = message;
        this.clientUser = clientUser;
    }

    @NotNull
    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(@NotNull final String groupId)
    {
        this.groupId = groupId;
    }

    @NotNull
    public String getMessage()
    {
        return message;
    }

    public void setMessage(@NotNull final String message)
    {
        this.message = message;
    }

    @Nullable
    public ClientUser getClientUser()
    {
        return clientUser;
    }

    public void setClientUser(@Nullable final ClientUser clientUser)
    {
        this.clientUser = clientUser;
    }
}
