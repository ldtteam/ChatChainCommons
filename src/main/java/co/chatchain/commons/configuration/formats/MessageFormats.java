package co.chatchain.commons.configuration.formats;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("CanBeFinal")
@ConfigSerializable
public class MessageFormats
{
    @Setting("generic")
    private List<String> genericMessage = new ArrayList<>();

    @Setting("client-event")
    private Map<String, List<String>> clientEventMessages = new HashMap<>();

    @Setting("user-event")
    private Map<String, List<String>> userEventMessages = new HashMap<>();

    public List<String> getGenericMessage()
    {
        return genericMessage;
    }

    public Map<String, List<String>> getClientEventMessages()
    {
        return clientEventMessages;
    }

    public Map<String, List<String>> getUserEventMessages()
    {
        return userEventMessages;
    }
}
