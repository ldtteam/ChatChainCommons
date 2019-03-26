package co.chatchain.commons.messages.objects.messages;

import co.chatchain.commons.messages.interfaces.messages.IUserEventMessage;
import co.chatchain.commons.messages.objects.Client;
import co.chatchain.commons.messages.objects.User;

import java.util.Map;

public class UserEventMessage implements IUserEventMessage<User, Client>
{
    private String event;
    private User user;
    private Client sendingClient;
    private boolean sendToSelf;
    private Map<String, String> extraEventData;

    public UserEventMessage(final String event, final User user, final Boolean sendToSelf, final Map<String, String> extraEventData)
    {
        this.event = event;
        this.user = user;
        this.sendToSelf = sendToSelf;
        this.extraEventData = extraEventData;
    }

    public UserEventMessage(final String event, final User user, final Boolean sendToSelf)
    {
        this(event, user, sendToSelf, null);
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
    public Client getSendingClient()
    {
        return this.sendingClient;
    }

    @Override
    public boolean hasSendToSelf()
    {
        return this.sendToSelf;
    }

    @Override
    public Map<String, String> getExtraEventData()
    {
        return this.extraEventData;
    }
}
