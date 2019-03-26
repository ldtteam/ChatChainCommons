package co.chatchain.commons.messages.interfaces.message;

import co.chatchain.commons.messages.interfaces.IClient;

import java.util.Map;

public interface IClientEventMessage<T1 extends IClient>
{

    String getEvent();

    T1 getClient();

    boolean hasSendToSelf();

    Map<String, String> getExtraEventData();

}
