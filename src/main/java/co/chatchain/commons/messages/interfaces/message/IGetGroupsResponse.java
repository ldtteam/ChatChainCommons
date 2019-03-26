package co.chatchain.commons.messages.interfaces.message;

import co.chatchain.commons.messages.interfaces.IGroup;

import java.util.List;

public interface IGetGroupsResponse<T1 extends IGroup>
{

    List<T1> getGroups();

}
