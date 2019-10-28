package co.chatchain.commons.objects;

import java.util.List;

public class ClientUser
{
    private String name;
    private String uniqueId;
    private String nickName;
    private String colour;
    private List<ClientRank> clientRanks;

    public ClientUser()
    {
    }

    public ClientUser(final String name, final String uniqueId, final String nickName, final String colour, final List<ClientRank> clientRanks)
    {
        this.name = name;
        this.uniqueId = uniqueId;
        this.nickName = nickName;
        this.colour = colour;
        this.clientRanks = clientRanks;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getUniqueId()
    {
        return uniqueId;
    }

    public void setUniqueId(final String uniqueId)
    {
        this.uniqueId = uniqueId;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(final String nickName)
    {
        this.nickName = nickName;
    }

    public String getColour()
    {
        return colour;
    }

    public void setColour(final String colour)
    {
        this.colour = colour;
    }

    public List<ClientRank> getClientRanks()
    {
        return clientRanks;
    }

    public void setClientRanks(final List<ClientRank> clientRanks)
    {
        this.clientRanks = clientRanks;
    }
}
