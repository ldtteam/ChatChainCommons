package co.chatchain.commons.messages.interfaces.message;

import co.chatchain.commons.messages.interfaces.IClient;
import co.chatchain.commons.messages.interfaces.IUser;

public interface IUserEventMessage<T1 extends IUser, T2 extends IClient>
{

    String getEvent();

    T1 getUser();

    T2 getClient();

    boolean hasSendToSelf();

}
