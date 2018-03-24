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
    private String sourceLanguage;
    private String translationPair;
    private final HttpClient client = HttpClientBuilder.create().build();

    public YandexTransalteClient(String targetLanguage, String sourceLanguage) {
        super(targetLanguage);
        this.sourceLanguage = sourceLanguage;
        this.setTranslationPair(sourceLanguage, targetLanguage);
    }

    public YandexTransalteClient(String targetLanguage) {
        super(targetLanguage);
    }

    public void setTranslationPair(String sourceLanguage, String targetLanguage) {
        this.translationPair = sourceLanguage + "-" + targetLanguage;
    }

    public String getTranslationPair() {
        return translationPair;
    }

    public String getSourceLang() {
        return sourceLanguage;
    }

    public void setSourceLang(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    @Override
    public Map<String, String> traslateString(String input) {
        Map<String, String> result = new HashMap<String, String>();
        //doddac mozliwosc podania source lang[]
        if (input.substring(input.length() - 1, input.length()).equals("\n")) {
            input = input.substring(0, input.length() - 1);
        }
        try {
            String lang;
            if (this.getTranslationPair() == null) {
                lang = this.getTargetLanguage();
            } else {
                lang = this.getTranslationPair();
            }

            StringBuilder url = new StringBuilder("https://translate.yandex.net/api/v1.5/tr.json/translate");
            url.append("?key=trnsl.1.1.20180321T182707Z.b7b832ef3316dcdd.7a1a61c27e0904114a56d4fb516c2c9db15360a3")
                    .append("&text=")
                    .append(URLEncoder.encode(input, "UTF-8"))
                    .append("&lang=")
                    .append(lang);
            String encodedURL = url.toString();
            HttpGet request = new HttpGet(encodedURL);
            HttpResponse response = client.execute(request);
            String entity = EntityUtils.toString(response.getEntity());
            JSONObject JSONRepsonse = new JSONObject(entity);
            String text = ((JSONArray) JSONRepsonse.get("text")).getString(0);
            lang = JSONRepsonse.getString("lang").substring(0, 2);
            LOGGER.log(Level.FINE, "\nSending ''GET'' request to URL : {0}", url);
            LOGGER.log(Level.FINE, "Response Code : {0}", response.getStatusLine());
            EntityUtils.consume(response.getEntity());
            result.put("lang", lang);
            result.put("text", text);
            return result;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
