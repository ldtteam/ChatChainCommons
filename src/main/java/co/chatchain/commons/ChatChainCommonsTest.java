package co.chatchain.commons;

import co.chatchain.commons.configuration.ConfigurationModule;
import co.chatchain.commons.core.CoreModule;
import co.chatchain.commons.core.cases.ReceiveClientEventCase;
import co.chatchain.commons.core.cases.ReceiveGenericMessageCase;
import co.chatchain.commons.core.cases.ReceiveUserEventCase;
import co.chatchain.commons.core.entities.Client;
import co.chatchain.commons.core.entities.ClientRank;
import co.chatchain.commons.core.entities.ClientUser;
import co.chatchain.commons.core.entities.Group;
import co.chatchain.commons.core.entities.messages.ClientEventMessage;
import co.chatchain.commons.core.entities.messages.GenericMessageMessage;
import co.chatchain.commons.core.entities.messages.UserEventMessage;
import co.chatchain.commons.core.interfaces.IMessageSender;
import co.chatchain.commons.infrastructure.InfrastructureModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ChatChainCommonsTest
{
    public static void main(String[] args)
    {
        File configDir = new File(System.getProperty("user.dir") + "/configs/");

        if (!configDir.exists())
        {
            //noinspection ResultOfMethodCallIgnored
            configDir.mkdirs();

            if (!configDir.getParentFile().exists())
            {
                System.out.println("Couldn't create config directory!");
                new IOException().printStackTrace();
            }
        }

        Injector injector = Guice.createInjector(new CoreModule(), new InfrastructureModule(), new ConfigurationModule(configDir.toPath().resolve("formatting.json"), true), new TestingModule());

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
        receiveClientEventCase.handle(clientEventMessage);

        final ClientRank clientRank = new ClientRank("sending-client-user-rank-name", "sending-client-user-rank-unique-id", 0, "sending-client-user-rank-display", "sending-client-user-rank-colour");

        final ClientUser clientUser = new ClientUser("sending-client-user-name", "sending-client-user-unique-id", null, "sending-client-user-colour", Collections.singletonList(clientRank));

        final GenericMessageMessage genericMessageMessage = new GenericMessageMessage(
                sendingClient,
                "receiving-client-id",
                sendingGroup,
                "message",
                clientUser);

        ReceiveGenericMessageCase receiveGenericMessageCase = injector.getInstance(ReceiveGenericMessageCase.class);
        receiveGenericMessageCase.handle(genericMessageMessage);

        final UserEventMessage userEventMessage = new UserEventMessage(
                sendingClient,
                clientUser,
                "receiving-client-id",
                sendingGroup,
                new ArrayList<>(),
                "LOGIN",
                new HashMap<>());

        ReceiveUserEventCase receiveUserEventCase = injector.getInstance(ReceiveUserEventCase.class);
        receiveUserEventCase.handle(userEventMessage);
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

    protected static class TestingModule extends AbstractModule
    {
        @Override
        protected void configure()
        {
            bind(IMessageSender.class).to(MessageSender.class);
        }
    }
}
