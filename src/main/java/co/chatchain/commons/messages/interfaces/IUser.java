package co.chatchain.commons.messages.interfaces;

import java.util.List;

public interface IUser<T1 extends IClientRank>
{

    String getName();

    String getUniqueId();

    String getNickName();

    String getColour();

    List<T1> getClientRanks();
}
