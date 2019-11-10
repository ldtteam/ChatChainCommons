package co.chatchain.commons;

import co.chatchain.commons.configuration.ConfigurationModule;
import co.chatchain.commons.core.CoreModule;
import co.chatchain.commons.core.cases.ReceiveClientEventCase;
import co.chatchain.commons.core.cases.ReceiveGenericMessageCase;
import co.chatchain.commons.core.cases.ReceiveUserEventCase;
import co.chatchain.commons.core.entites.Client;
import co.chatchain.commons.core.entites.ClientRank;
import co.chatchain.commons.core.entites.ClientUser;
import co.chatchain.commons.core.entites.Group;
import co.chatchain.commons.core.entites.messages.ClientEventMessage;
import co.chatchain.commons.core.entites.messages.GenericMessageMessage;
import co.chatchain.commons.core.entites.messages.UserEventMessage;
import co.chatchain.commons.core.interfaces.IMessageSender;
import co.chatchain.commons.infrastructure.InfrastructureModule;
import co.chatchain.commons.infrastructure.interfaces.configuration.IClientEventFormattingConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ChatChainCommonsTest
{
    public static void main(String[] args)
    {
        Injector injector = Guice.createInjector(new CoreModule(), new InfrastructureModule(), new ConfigurationModule(), new TestingModule());

        final Client sendingClient = new Client("sending-client-id", "sending-client-owner-id", "sending-client-name", "sending-client-description");
        final Group sendingGroup = new Group("sending-group-id", "sending-group-owner-id", "sending-group-name", "sending-group-description", new ArrayList<>());

        final ClientEventMessage clientEventMessage = new ClientEventMessage(
                sendingClient,
                "receiving-client-id",
                sendingGroup,
                new ArrayList<>(),
                "START",
                new HashMap<>());

        ReceiveClientEventCase receiveClientEventCase = injector.getInstance(ReceiveClientEventCase.class);
        receiveClientEventCase.Handle(clientEventMessage);

        final ClientRank clientRank = new ClientRank("sending-client-user-rank-name", "sending-client-user-rank-unique-id", 0, "sending-client-user-rank-display", "sending-client-user-rank-colour");

        final ClientUser clientUser = new ClientUser("sending-client-user-name", "sending-client-user-unique-id", "sending-client-user-nick-name", "sending-client-user-colour", Collections.singletonList(clientRank));

        final GenericMessageMessage genericMessageMessage = new GenericMessageMessage(
                sendingClient,
                "receiving-client-id",
                sendingGroup,
                "message",
                clientUser);

        ReceiveGenericMessageCase receiveGenericMessageCase = injector.getInstance(ReceiveGenericMessageCase.class);
        receiveGenericMessageCase.Handle(genericMessageMessage);

        final UserEventMessage userEventMessage = new UserEventMessage(
                sendingClient,
                clientUser,
                "receiving-client-id",
                sendingGroup,
                new ArrayList<>(),
                "LOGIN",
                new HashMap<>());

        ReceiveUserEventCase receiveUserEventCase = injector.getInstance(ReceiveUserEventCase.class);
        receiveUserEventCase.Handle(userEventMessage);
    }

    public static class MessageSender implements IMessageSender
    {
        @Override
        public boolean sendMessage(final String message, final Group group)
        {
            System.out.println(group.getName() + " : " + message);
            return true;
        }
    }

    public static class ClientEventFormattingConfig implements IClientEventFormattingConfig
    {
        @Override
        public String[] getClientEventFormattingString(final ClientEventMessage message)
        {
            return new String[] {"[{group-name}] ", "{client-name} has connected"};
        }
    }

    public static class TestingModule extends AbstractModule
    {
        @Override
        protected void configure()
        {
            bind(IMessageSender.class).to(MessageSender.class);
        }
    }
}
