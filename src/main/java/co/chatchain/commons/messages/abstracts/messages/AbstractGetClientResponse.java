package co.chatchain.commons.messages.abstracts.messages;

import co.chatchain.commons.messages.abstracts.AbstractClient;

public abstract class AbstractGetClientResponse<T1 extends AbstractClient>
{
    protected T1 client;

    public T1 getClient()
    {
        return this.client;
    }

    public void setClient(final T1 client)
    {
        this.client = client;
    }

}
