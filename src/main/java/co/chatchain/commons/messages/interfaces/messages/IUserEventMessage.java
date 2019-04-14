package co.chatchain.commons.messages.interfaces.messages;

import co.chatchain.commons.messages.interfaces.IClient;
import co.chatchain.commons.messages.interfaces.IGroup;
import co.chatchain.commons.messages.interfaces.IUser;

import java.util.Map;

public interface IUserEventMessage<T1 extends IUser, T2 extends IClient, T3 extends IGroup>
{

    String getEvent();

    T3 getGroup();

    T1 getUser();

    T2 getSendingClient();

    boolean hasSendToSelf();

    Map<String, String> getExtraEventData();

}
