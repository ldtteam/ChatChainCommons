package co.chatchain.commons;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class AccessTokenResolver
{

    private String clientId;
    private String clientPassword;
    private URL identityUrl;

    public AccessTokenResolver(final String clientId, final String clientPassword, final String identityUrl) throws MalformedURLException
    {
        this.clientId = clientId;
        this.clientPassword = clientPassword;
        this.identityUrl = new URL(identityUrl);
    }

    public String getAccessToken()
    {
        try
        {
            URLConnection con = identityUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            Map<String, String> arguments = new HashMap<>();
            arguments.put("client_id", clientId);
            arguments.put("client_secret", clientPassword);
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
        catch (IOException e)
        {
            System.out.println("Problem with getting access token: ");
            e.printStackTrace();
            return null;
        }
    }

}
