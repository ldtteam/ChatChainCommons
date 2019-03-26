package co.chatchain.commons.messages.interfaces.messages;

import co.chatchain.commons.messages.interfaces.IClient;
import co.chatchain.commons.messages.interfaces.IGroup;
import co.chatchain.commons.messages.interfaces.IUser;

public interface IGenericMessage<T1 extends IGroup, T2 extends IClient, T3 extends IUser>
{

    T1 getGroup();

    T2 getSendingClient();

    T3 getUser();

    String getMessage();

    boolean hasSendToSelf();

}
