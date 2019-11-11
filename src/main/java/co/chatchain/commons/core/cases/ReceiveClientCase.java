package co.chatchain.commons.core.cases;

import co.chatchain.commons.core.entities.messages.GetClientMessage;
import co.chatchain.commons.core.interfaces.cases.IReceiveClientCase;
import co.chatchain.commons.interfaces.IChatChainHubConnection;

import javax.inject.Inject;

public class ReceiveClientCase implements IReceiveClientCase
{

    private final IChatChainHubConnection hubConnection;

    @Inject
    public ReceiveClientCase(final IChatChainHubConnection hubConnection)
    {
        this.hubConnection = hubConnection;
    }

    @Override
    public boolean handle(final GetClientMessage message)
    {
        hubConnection.setClient(message.getClient());
        return true;
    }
}
