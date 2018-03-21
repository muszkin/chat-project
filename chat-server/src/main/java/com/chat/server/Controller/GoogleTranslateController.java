/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.server.Controller;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 *
 * @author kari
 */
public class GoogleTranslateController implements TranslationInterface {
    private String sourceLanguage;
    private String targetLanguage;

    public GoogleTranslateController(String sourceLanguage, String targetLanguage) {
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }
    
    @Override
    public String traslateString(String input) {
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        Translation translation
                = translate.translate(
                        input,
                        TranslateOption.sourceLanguage(this.getSourceLanguage()),
                        TranslateOption.targetLanguage(this.getTargetLanguage()));

        return translation.getTranslatedText();
    }
}
