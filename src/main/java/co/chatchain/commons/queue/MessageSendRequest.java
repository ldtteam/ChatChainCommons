package co.chatchain.commons.queue;

import co.chatchain.commons.core.interfaces.IMessage;
import com.microsoft.signalr.HubConnection;

public class MessageSendRequest<T extends IMessage>
{
    private final SendAction<T> sendAction;

    public MessageSendRequest(final SendAction<T> sendAction)
    {
        this.sendAction = sendAction;
    }

    public SendAction<T> getSendAction()
    {
        return sendAction;
    }

    public interface SendAction<T extends IMessage>
    {
        void invoke(HubConnection connection);
    }
}
