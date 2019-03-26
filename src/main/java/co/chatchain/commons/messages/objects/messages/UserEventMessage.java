package co.chatchain.commons.messages.objects.messages;

import co.chatchain.commons.messages.abstracts.messages.AbstractUserEventMessage;
import co.chatchain.commons.messages.objects.Client;
import co.chatchain.commons.messages.objects.User;

import java.util.Map;

public class UserEventMessage extends AbstractUserEventMessage<User, Client>
{

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

}
