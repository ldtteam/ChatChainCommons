package co.chatchain.commons.messages.interfaces.messages;

import co.chatchain.commons.messages.interfaces.IClient;

import java.util.Map;

public interface IClientEventMessage<T1 extends IClient>
{

    String getEvent();

    T1 getSendingClient();

    boolean hasSendToSelf();

    Map<String, String> getExtraEventData();

}
