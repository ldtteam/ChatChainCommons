package co.chatchain.commons.interfaces;

import java.io.IOException;

public interface IAccessTokenResolver
{
    String getAccessToken() throws IOException;
}
