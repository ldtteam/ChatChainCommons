package co.chatchain.commons.configuration.formats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class simply adds a constructor for the parent class
 * for default values.
 */
public class MessageFormatsRoot extends MessageFormats
{
    public MessageFormatsRoot(final boolean advanced)
    {
        super(advanced);

        this.genericMessage = new ArrayList<String>()
        {
            {
                add("[{client-name}] ");
                add("[{client-rank-display}] ");
                add("<{client-user-nickname||client-user-name}>: {message}");
            }
        };

        this.clientEventMessages = new HashMap<String, List<String>>()
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

        this.userEventMessages = new HashMap<String, List<String>>()
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

        this.statsResponse = new ArrayList<String>()
        {
            {
                add("[Stats]\n");
                add("[Stats] [Client Name]: {client-name}\n");
                add("[Stats] [Online Users]: {online-users}\n");
                add("[Stats] [Performance]: {performance} {performance-name}\n");
                add("[Stats]\n");
            }
        };
    }
}
