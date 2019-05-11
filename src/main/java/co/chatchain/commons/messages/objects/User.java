package co.chatchain.commons.messages.objects;

import co.chatchain.commons.messages.interfaces.IUser;

import java.util.ArrayList;
import java.util.List;

public class User implements IUser<ClientRank>
{

    private String name;
    private String uniqueId;
    private String nickName;
    private List<ClientRank> clientRanks;

    public User(final String name, final String uniqueId, final String nickName, final List<ClientRank> clientRanks)
    {
        this.name = name;
        this.uniqueId = uniqueId;
        this.clientRanks = clientRanks;
    }

    public User(final String name, final String uniqueId, final String nickName)
    {
        this(name, uniqueId, nickName, new ArrayList<>());
    }

    public User(final String name, final String uniqueId)
    {
        this(name, uniqueId, null, new ArrayList<>());
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public String getUniqueId()
    {
        return this.uniqueId;
    }

    @Override
    public String getNickName()
    {
        return this.nickName;
    }

    @Override
    public List<ClientRank> getClientRanks()
    {
        return this.clientRanks;
    }
}
