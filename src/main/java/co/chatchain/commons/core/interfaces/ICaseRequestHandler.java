package co.chatchain.commons.core.interfaces;

public interface ICaseRequestHandler<T extends IMessage>
{
    /**
     * handle the message T
     * @param message The message defined by the child interface that is to be handled.
     * @return Was the handling successful?
     */
    @SuppressWarnings("UnusedReturnValue")
    boolean handle(T message);
}
