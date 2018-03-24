package com.chat.server.Translation;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public YandexTransalteClient(String targetLanguage, String sourceLanguage) {
        super(targetLanguage);
        this.sourceLanguage = sourceLanguage;
        this.setTranslationPair(sourceLanguage, targetLanguage);
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
    public Map<String,String> traslateString(String input) {
        Map<String,String> result = new HashMap<String,String>();
        //doddac mozliwosc podania source lang[]
        URL url;
        try {
            
            String lang = null;
            if (this.getTranslationPair() == null) {
                lang = this.getTargetLanguage();
            } else {
                lang = this.getTranslationPair();
            }
            
            url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Map<String, String> parameters = new HashMap<>();
            parameters.put("key", "trnsl.1.1.20180321T182707Z.b7b832ef3316dcdd.7a1a61c27e0904114a56d4fb516c2c9db15360a3");
            parameters.put("text", input);
            parameters.put("lang", lang);

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();

            int responseCode = con.getResponseCode();
            LOGGER.log(Level.FINE, "\nSending ''GET'' request to URL : {0}", url);
            LOGGER.log(Level.FINE, "Response Code : {0}", responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject JSONRepsonse = new JSONObject(response.toString());
            String text = ((JSONArray) JSONRepsonse.get("text")).getString(0);
            String detectedLang =  ((JSONArray) JSONRepsonse.get("text")).getString(0);
            result.put("lang" ,detectedLang);
            result.put("text", text);
            return result;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
