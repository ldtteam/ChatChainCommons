package co.chatchain.commons.messages.objects.message;

import co.chatchain.commons.messages.interfaces.IClient;
import co.chatchain.commons.messages.interfaces.message.IGetClientResponse;

public class GetClientResponse<T1 extends IClient> implements IGetClientResponse<T1>
{
    private T1 client;

    @Override
    public T1 getClient()
    {
        return this.client;
    }
}
