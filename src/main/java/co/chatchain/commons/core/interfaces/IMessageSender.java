package co.chatchain.commons.core.interfaces;

import co.chatchain.commons.core.entities.Group;

@SuppressWarnings("SameReturnValue")
public interface IMessageSender
{

    /**
     * Send the given message in the given group on a client.
     * @param message Message as given by the use case receiver.
     * @param group the Group for the message to be sent in.
     * @return Whether sending the message was successful.
     */
    boolean sendMessage(final String message, final Group group);

    /**
     * Send the given stats message to a responseLocation.
     * @param message Message as given by the use case receiver.
     * @param responseLocation the unique identifier for the location (group, user, whatever) that the client has decided to send this request response,
     * @return Whether sending the message was successful.
     */
    boolean sendStatsMessage(final String message, final String responseLocation);
}
