package com.chat.server.Translation;

import static com.chat.server.Translation.YandexTransalteClient.LOGGER;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author kari
 */
public class YandexDetectorClient extends AbstractDetectorClient {

    @Override
    public void detect(String input) {

        StringBuilder url = new StringBuilder("https://translate.yandex.net/api/v1.5/tr.json/detect");
        try {
            url
                .append("?key=trnsl.1.1.20180321T182707Z.b7b832ef3316dcdd.7a1a61c27e0904114a56d4fb516c2c9db15360a3")
                .append("&text=")
                .append(URLEncoder.encode(input, "UTF-8"));
            
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url.toString());
            HttpResponse response = client.execute(request);
            String entity = EntityUtils.toString(response.getEntity());
            JSONObject JSONRepsonse = new JSONObject(entity);
            String detectedLanguage = JSONRepsonse.getString("lang");
            this.setDetectedLanguage(detectedLanguage);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
