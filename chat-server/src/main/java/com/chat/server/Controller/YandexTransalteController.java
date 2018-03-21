/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.server.Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author kari
 */
public class YandexTransalteController implements TranslationInterface {

    private String targetLanguage;

    public YandexTransalteController(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
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
            return JSONRepsonse.get("text").toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(YandexTransalteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(YandexTransalteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(YandexTransalteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(YandexTransalteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "false";
    }
}
