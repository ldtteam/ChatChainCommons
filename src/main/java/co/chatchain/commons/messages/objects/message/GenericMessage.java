package co.chatchain.commons.messages.objects.message;

import co.chatchain.commons.messages.interfaces.message.IGenericMessage;
import co.chatchain.commons.messages.objects.Client;
import co.chatchain.commons.messages.objects.Group;
import co.chatchain.commons.messages.objects.User;

public class GenericMessage implements IGenericMessage<Group, Client, User>
{

    private Group group;
    private Client sendingClient;
    private User user;
    private String message;
    private boolean sendToSelf;

    public GenericMessage(final Group group, final User user, final String message, final boolean sendToSelf)
    {
        this.group = group;
        this.user = user;
        this.message = message;
        this.sendToSelf = sendToSelf;
    }

    public GenericMessage(final Group group, final User user, final String message)
    {
        this(group, user, message, false);
    }

    @Override
    public Group getGroup()
    {
        return this.group;
    }

    @Override
    public Client getSendingClient()
    {
        return this.sendingClient;
    }

    @Override
    public User getUser()
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
