package co.chatchain.commons.core.entities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("NullableProblems")
public class Group
{

    @NotNull
    private String id;
    @NotNull
    private String ownerId;
    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private List<String> clientIds;

    public Group()
    {
    }

    public Group(@NotNull final String id, @NotNull final String ownerId)
    {
        this.id = id;
        this.ownerId = ownerId;
    }

    public Group(@NotNull final String id, @NotNull final String ownerId, @Nullable final String name, @Nullable final String description, @Nullable final List<String> clientIds)
    {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.clientIds = clientIds;
    }

    @NotNull
    public String getId()
    {
        return id;
    }

    public void setId(@NotNull final String id)
    {
        this.id = id;
    }

    @NotNull
    public String getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(@NotNull final String ownerId)
    {
        this.ownerId = ownerId;
    }

    @Nullable
    public String getName()
    {
        return name;
    }

    public void setName(@Nullable final String name)
    {
        this.name = name;
    }

    @Nullable
    public String getDescription()
    {
        return description;
    }

    public void setDescription(@Nullable final String description)
    {
        this.description = description;
    }

    @Nullable
    public List<String> getClientIds()
    {
        return clientIds;
    }

    public void setClientIds(@Nullable final List<String> clientIds)
    {
        this.clientIds = clientIds;
    }
}
