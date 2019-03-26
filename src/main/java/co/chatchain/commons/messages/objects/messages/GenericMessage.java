package co.chatchain.commons.messages.objects.messages;

import co.chatchain.commons.messages.abstracts.messages.AbstractGenericMessage;
import co.chatchain.commons.messages.objects.Client;
import co.chatchain.commons.messages.objects.Group;
import co.chatchain.commons.messages.objects.User;

public class GenericMessage extends AbstractGenericMessage<Group, Client, User>
{


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
}
