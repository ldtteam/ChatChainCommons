package co.chatchain.commons.queue;

import co.chatchain.commons.interfaces.IChatChainHubConnection;
import com.microsoft.signalr.HubConnectionState;

import javax.inject.Inject;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageConsumer implements Runnable
{
    private final LinkedBlockingQueue<MessageSendRequest> queue;
    private final IChatChainHubConnection connection;

    @Inject
    public MessageConsumer(final LinkedBlockingQueue<MessageSendRequest> queue, final IChatChainHubConnection connection)
    {
        this.queue = queue;
        this.connection = connection;
    }

    @Override
    public void run()
    {
        while (connection != null && connection.getConnection().getConnectionState().equals(HubConnectionState.CONNECTED))
        {
            try
            {
                final MessageSendRequest sendRequest = queue.take();
                sendRequest.getSendAction().invoke(connection.getConnection());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
