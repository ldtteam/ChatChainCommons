package co.chatchain.commons.messages.abstracts.messages;

import co.chatchain.commons.messages.abstracts.AbstractClient;
import co.chatchain.commons.messages.abstracts.AbstractGroup;
import co.chatchain.commons.messages.abstracts.AbstractUser;

public abstract class AbstractGenericMessage<T1 extends AbstractGroup, T2 extends AbstractClient, T3 extends AbstractUser>
{

    protected T1 group;
    protected T2 sendingClient;
    protected T3 user;
    protected String message;
    protected boolean sendToSelf;

    public T1 getGroup()
    {
        return this.group;
    }

    public void setGroup(final T1 group)
    {
        this.group = group;
    }

    public T2 getSendingClient()
    {
        return this.sendingClient;
    }

    public void setSendingClient(final T2 sendingClient)
    {
        this.sendingClient = sendingClient;
    }

    public T3 getUser()
    {
        return this.user;
    }

    public void setUser(final T3 user)
    {
        this.user = user;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(final String message)
    {
        this.message = message;
    }

    public boolean hasSendToSelf()
    {
        return this.sendToSelf;
    }

    public void setSendToSelf(final Boolean sendToSelf)
    {
        this.sendToSelf = sendToSelf;
    }

}
