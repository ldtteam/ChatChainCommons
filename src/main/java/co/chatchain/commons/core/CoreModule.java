package co.chatchain.commons.core;

import co.chatchain.commons.core.cases.ReceiveClientCase;
import co.chatchain.commons.core.cases.events.ReceiveClientEventCase;
import co.chatchain.commons.core.cases.ReceiveGenericMessageCase;
import co.chatchain.commons.core.cases.events.ReceiveUserEventCase;
import co.chatchain.commons.core.cases.stats.ReceiveStatsResponseCase;
import co.chatchain.commons.core.interfaces.cases.IReceiveClientCase;
import co.chatchain.commons.core.interfaces.cases.events.IReceiveClientEventCase;
import co.chatchain.commons.core.interfaces.cases.IReceiveGenericMessageCase;
import co.chatchain.commons.core.interfaces.cases.events.IReceiveUserEventCase;
import co.chatchain.commons.core.interfaces.cases.stats.IReceiveStatsResponseCase;
import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(IReceiveClientCase.class).to(ReceiveClientCase.class);
        bind(IReceiveClientEventCase.class).to(ReceiveClientEventCase.class);
        bind(IReceiveGenericMessageCase.class).to(ReceiveGenericMessageCase.class);
        bind(IReceiveUserEventCase.class).to(ReceiveUserEventCase.class);
        bind(IReceiveStatsResponseCase.class).to(ReceiveStatsResponseCase.class);
    }
}
