package co.chatchain.commons;

import co.chatchain.commons.interfaces.IAccessTokenResolver;
import co.chatchain.commons.interfaces.IConnectionConfig;
import org.json.JSONObject;

import javax.inject.Inject;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class AccessTokenResolver implements IAccessTokenResolver
{

    private final IConnectionConfig connectionConfig;

    @Inject
    public AccessTokenResolver(IConnectionConfig connectionConfig)
    {
        this.connectionConfig = connectionConfig;
    }

    @Override
    public String getAccessToken() throws IOException
    {
        URLConnection con = connectionConfig.getIdentityUrl().openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        Map<String, String> arguments = new HashMap<>();
        arguments.put("client_id", connectionConfig.getClientId());
        arguments.put("client_secret", connectionConfig.getClientSecret());
        arguments.put("grant_type", "client_credentials");
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : arguments.entrySet())
        {
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        http.setFixedLengthStreamingMode(length);
        http.connect();
        try (OutputStream os = http.getOutputStream())
        {
            os.write(out);
        }

        Scanner s = new Scanner(http.getInputStream()).useDelimiter("\\A");
        String output = s.hasNext() ? s.next() : "";

        JSONObject jsonObject = new JSONObject(output);

        return jsonObject.getString("access_token");
    }

}
