package co.chatchain.commons.core.interfaces;

public interface ICaseRequestHandler<T extends IMessage>
{
    /**
     * Handle the message T
     * @param message The message defined by the child interface that is to be handled.
     * @return Was the handling successful?
     */
    boolean Handle(T message);
}
