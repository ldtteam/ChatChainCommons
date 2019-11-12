package co.chatchain.commons;

import co.chatchain.commons.interfaces.IAccessTokenResolver;
import co.chatchain.commons.interfaces.IChatChainHubConnection;
import com.google.inject.AbstractModule;

public class HubModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(IAccessTokenResolver.class).to(AccessTokenResolver.class);
        bind(IChatChainHubConnection.class).to(ChatChainHubConnection.class);
    }
}
