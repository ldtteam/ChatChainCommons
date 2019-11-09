package co.chatchain.commons.objects;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClientUser
{

    @Nullable
    private String name;
    @Nullable
    private String uniqueId;
    @Nullable
    private String nickName;
    @Nullable
    private String colour;
    @Nullable
    private List<ClientRank> clientRanks;

    public ClientUser()
    {
    }

    public ClientUser(@Nullable final String name, @Nullable final String uniqueId, @Nullable final String nickName, @Nullable final String colour, @Nullable final List<ClientRank> clientRanks)
    {
        this.name = name;
        this.uniqueId = uniqueId;
        this.nickName = nickName;
        this.colour = colour;
        this.clientRanks = clientRanks;
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
    public String getUniqueId()
    {
        return uniqueId;
    }

    public void setUniqueId(@Nullable final String uniqueId)
    {
        this.uniqueId = uniqueId;
    }

    @Nullable
    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(@Nullable final String nickName)
    {
        this.nickName = nickName;
    }

    @Nullable
    public String getColour()
    {
        return colour;
    }

    public void setColour(@Nullable final String colour)
    {
        this.colour = colour;
    }

    @Nullable
    public List<ClientRank> getClientRanks()
    {
        return clientRanks;
    }

    public void setClientRanks(@Nullable final List<ClientRank> clientRanks)
    {
        this.clientRanks = clientRanks;
    }
}
