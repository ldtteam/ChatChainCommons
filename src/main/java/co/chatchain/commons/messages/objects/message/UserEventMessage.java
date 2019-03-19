package co.chatchain.commons.messages.objects.message;

import co.chatchain.commons.messages.interfaces.message.IUserEventMessage;
import co.chatchain.commons.messages.objects.Client;
import co.chatchain.commons.messages.objects.User;

public class UserEventMessage implements IUserEventMessage<User, Client>
{
    private String event;
    private User user;
    private Client sendingClient;
    private boolean sendToSelf;

    public UserEventMessage(final String event, final User user, final Boolean sendToSelf)
    {
        this.event = event;
        this.user = user;
        this.sendToSelf = sendToSelf;
    }

    public UserEventMessage(final String event, final User user)
    {
        this(event, user, false);
    }

    @Override
    public String getEvent()
    {
        return this.event;
    }

    public User getUser() { return this.user;}

    @Override
    public Client getClient()
    {
        return this.sendingClient;
    }

    @Override
    public boolean hasSendToSelf()
    {
        return this.sendToSelf;
    }
}
