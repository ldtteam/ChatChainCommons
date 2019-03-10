package co.chatchain.commons.messages.interfaces.message;

import co.chatchain.commons.messages.interfaces.IClient;

public interface IClientEventMessage<T1 extends IClient>
{

    String getEvent();

    T1 getClient();

    boolean hasSendToSelf();

}
