package com.chat.server.Translation;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author kari
 */
public class YandexTransalteClient extends AbstractTranslationClient {

    static final Logger LOGGER = Logger.getLogger(YandexTransalteClient.class.getName());
    private String translationPair;
    private final HttpClient client = HttpClientBuilder.create().build();
    private final InputTrimmer trimmer = new InputTrimmer();

    public YandexTransalteClient(String sourceLanguage, String targetLanguage) {
        super(sourceLanguage, targetLanguage);
        this.setTranslationPair(sourceLanguage, targetLanguage);
    }

    public void setTranslationPair(String sourceLanguage, String targetLanguage) {
        this.translationPair = sourceLanguage + "-" + targetLanguage;
    }

    public String getTranslationPair() {
        return translationPair;
    }


    @Override
    public String traslateString(String input) {
        String translatedText = null;
        input = trimmer.trim(input);
        try {

            StringBuilder url = new StringBuilder("https://translate.yandex.net/api/v1.5/tr.json/translate");
            url.append("?key=trnsl.1.1.20180321T182707Z.b7b832ef3316dcdd.7a1a61c27e0904114a56d4fb516c2c9db15360a3")
                    .append("&text=")
                    .append(URLEncoder.encode(input, "UTF-8"))
                    .append("&lang=")
                    .append(this.getTranslationPair());
            String encodedURL = url.toString();
            HttpGet request = new HttpGet(encodedURL);
            HttpResponse response = client.execute(request);
            String entity = EntityUtils.toString(response.getEntity());
            JSONObject JSONRepsonse = new JSONObject(entity);
            translatedText = ((JSONArray) JSONRepsonse.get("text")).getString(0);
            LOGGER.log(Level.FINE, "\nSending ''GET'' request to URL : {0}", url);
            LOGGER.log(Level.FINE, "Response Code : {0}", response.getStatusLine());
            EntityUtils.consume(response.getEntity());
        
            return translatedText;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return translatedText;
    }
}
