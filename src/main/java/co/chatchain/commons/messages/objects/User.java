package co.chatchain.commons.messages.objects;

import co.chatchain.commons.messages.interfaces.IUser;

public class User implements IUser
{

    private String name;

    public User(final String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
