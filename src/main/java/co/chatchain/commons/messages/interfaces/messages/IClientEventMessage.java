package co.chatchain.commons.messages.interfaces.messages;

import co.chatchain.commons.messages.interfaces.IClient;
import co.chatchain.commons.messages.interfaces.IGroup;

import java.util.Map;

public interface IClientEventMessage<T1 extends IClient, T2 extends IGroup>
{

    String getEvent();

    T2 getGroup();

    T1 getSendingClient();

    boolean hasSendToSelf();

    Map<String, String> getExtraEventData();

}
