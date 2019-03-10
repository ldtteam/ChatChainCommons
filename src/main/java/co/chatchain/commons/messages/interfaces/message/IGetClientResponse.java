package co.chatchain.commons.messages.interfaces.message;

import co.chatchain.commons.messages.interfaces.IClient;

public interface IGetClientResponse<T1 extends IClient>
{

    T1 getClient();

}
