package co.chatchain.commons.configuration.formats;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Exactly the same as {@link MessageFormats} except it has default values
 */
@SuppressWarnings("CanBeFinal")
@ConfigSerializable
public class DefaultFormats extends MessageFormats
{
    @Setting("generic")
    private List<String> genericMessage = new ArrayList<String>()
    {
        {
            add("[{client-name}] ");
            add("[{client-rank-display}] ");
            add("<{client-user-nickname||client-user-name}>: {message}");
        }
    };

    @Setting("client-event")
    private Map<String, List<String>> clientEventMessages = new HashMap<String, List<String>>()
    {
        {
            put("START", new ArrayList<String>()
            {
                {
                    add("[{group-name}] ");
                    add("{client-name} has connected");
                }
            });

            put("STOP", new ArrayList<String>()
            {
                {
                    add("[{group-name}] ");
                    add("{client-name} has disconnected");
                }
            });
        }
    };

    @Setting("user-event")
    private Map<String, List<String>> userEventMessages = new HashMap<String, List<String>>()
    {
        {
            put("LOGIN", new ArrayList<String>()
            {
                {
                    add("[{group-name}] ");
                    add("[{client-name}] ");
                    add("{client-user-nickname||client-user-name} has logged in");
                }
            });
            put("LOGOUT", new ArrayList<String>()
            {
                {
                    add("[{group-name}] ");
                    add("[{client-name}] ");
                    add("{client-user-nickname||client-user-name} has logged out");
                }
            });
            put("DEATH", new ArrayList<String>()
            {
                {
                    add("[{group-name}] ");
                    add("[{client-name}] ");
                    add("{client-user-nickname||client-user-name} has died");
                }
            });
        }
    };

    @Override
    public List<String> getGenericMessage()
    {
        return genericMessage;
    }

    @Override
    public Map<String, List<String>> getClientEventMessages()
    {
        return clientEventMessages;
    }

    @Override
    public Map<String, List<String>> getUserEventMessages()
    {
        return userEventMessages;
    }
}
