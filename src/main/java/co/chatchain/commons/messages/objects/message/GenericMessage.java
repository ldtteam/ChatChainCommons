package co.chatchain.commons.messages.objects.message;

import co.chatchain.commons.messages.interfaces.IClient;
import co.chatchain.commons.messages.interfaces.IGroup;
import co.chatchain.commons.messages.interfaces.IUser;
import co.chatchain.commons.messages.interfaces.message.IGenericMessage;

public class GenericMessage<T1 extends IGroup, T2 extends IClient, T3 extends IUser> implements IGenericMessage<T1, T2, T3>
{

    private T1 group;
    private T2 client;
    private T3 user;
    private String message;
    private boolean sendToSelf;

    public GenericMessage(final T1 group, final T3 user, final String message, final boolean sendToSelf)
    {
        this.group = group;
        this.user = user;
        this.message = message;
        this.sendToSelf = sendToSelf;
    }

    @Override
    public T1 getGroup()
    {
        return this.group;
    }

    @Override
    public T2 getSendingClient()
    {
        return this.client;
    }

    @Override
    public T3 getUser()
    {
        return this.user;
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }

    @Override
    public boolean hasSendToSelf()
    {
        return this.sendToSelf;
    }
}
