package co.chatchain.commons.infrastructure;

import co.chatchain.commons.core.interfaces.formatters.events.IClientEventFormatter;
import co.chatchain.commons.core.interfaces.formatters.IGenericMessageFormatter;
import co.chatchain.commons.core.interfaces.formatters.events.IUserEventFormatter;
import co.chatchain.commons.core.interfaces.formatters.stats.IStatsResponseFormatter;
import co.chatchain.commons.infrastructure.formatters.events.ClientEventFormatter;
import co.chatchain.commons.infrastructure.formatters.GenericMessageFormatter;
import co.chatchain.commons.infrastructure.formatters.events.UserEventFormatter;
import co.chatchain.commons.infrastructure.formatters.stats.StatsResponseFormatter;
import co.chatchain.commons.infrastructure.interfaces.replacements.*;
import co.chatchain.commons.infrastructure.replacements.*;
import com.google.inject.AbstractModule;

public class InfrastructureModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        //Formatters
        bind(IClientEventFormatter.class).to(ClientEventFormatter.class);
        bind(IGenericMessageFormatter.class).to(GenericMessageFormatter.class);
        bind(IUserEventFormatter.class).to(UserEventFormatter.class);
        bind(IStatsResponseFormatter.class).to(StatsResponseFormatter.class);

        //Replacements
        bind(IClientRankReplacements.class).to(ClientRankReplacements.ClientRankReplacementsInstance.class);
        bind(IClientReplacements.class).to(ClientReplacements.ClientReplacementsInstance.class);
        bind(IClientUserReplacements.class).to(ClientUserReplacements.ClientUserReplacementsInstance.class);
        bind(IGenericMessageReplacements.class).to(GenericMessageReplacements.GenericMessageReplacementsInstance.class);
        bind(IGroupReplacements.class).to(GroupReplacements.GroupReplacementsInstance.class);
        bind(IStatsObjectReplacements.class).to(StatsObjectReplacements.StatsObjectReplacementsInstance.class);
    }
}
