package com.chat.server.Translation;

import com.chat.server.Controller.ParameterStringBuilder;
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
public class YandexTransalteController extends TranslationInterface {

  Logger logger = Logger.getLogger(YandexTransalteController.class.getName());

  public YandexTransalteController(String targetLanguage) {
    super(targetLanguage);
  }

  @Override
  public String traslateString(String input) {

    URL url;
    try {
      url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      Map<String, String> parameters = new HashMap<>();
      parameters.put("key", "trnsl.1.1.20180321T182707Z.b7b832ef3316dcdd.7a1a61c27e0904114a56d4fb516c2c9db15360a3");
      parameters.put("text", input);
      parameters.put("lang", this.getTargetLanguage());

      con.setDoOutput(true);
      DataOutputStream out = new DataOutputStream(con.getOutputStream());
      out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
      out.flush();
      out.close();

      int responseCode = con.getResponseCode();
      System.out.println("\nSending 'GET' request to URL : " + url);
      System.out.println("Response Code : " + responseCode);

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
      return text;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, null, ex);
    }

    return "false";
  }
}
