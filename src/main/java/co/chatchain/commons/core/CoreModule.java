package co.chatchain.commons.core;

import co.chatchain.commons.core.cases.ReceiveClientEventCase;
import co.chatchain.commons.core.cases.ReceiveGenericMessageCase;
import co.chatchain.commons.core.cases.ReceiveUserEventCase;
import co.chatchain.commons.core.interfaces.cases.IReceiveClientEventCase;
import co.chatchain.commons.core.interfaces.cases.IReceiveGenericMessageCase;
import co.chatchain.commons.core.interfaces.cases.IReceiveUserEventCase;
import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(IReceiveClientEventCase.class).to(ReceiveClientEventCase.class);
        bind(IReceiveGenericMessageCase.class).to(ReceiveGenericMessageCase.class);
        bind(IReceiveUserEventCase.class).to(ReceiveUserEventCase.class);
    }
}
