package co.chatchain.commons.messages.interfaces.messages;

import co.chatchain.commons.messages.interfaces.IClient;

public interface IGetClientResponse<T1 extends IClient>
{

    T1 getClient();

}
