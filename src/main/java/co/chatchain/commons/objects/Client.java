package co.chatchain.commons.objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Client
{

    @NotNull
    private String id;
    @NotNull
    private String ownerId;
    @Nullable
    private String name;
    @Nullable
    private String description;

    public Client(@NotNull final String id, @NotNull final String ownerId)
    {
        this.id = id;
        this.ownerId = ownerId;
    }

    public Client(@NotNull final String id, @NotNull final String ownerId, @Nullable final String name, @Nullable final String description)
    {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
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
}
