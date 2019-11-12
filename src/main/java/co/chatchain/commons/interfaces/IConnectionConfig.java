package co.chatchain.commons.interfaces;

import java.net.URL;

public interface IConnectionConfig
{
    String getClientId();
    String getClientSecret();
    URL getIdentityUrl();
    URL getHubUrl();
}
