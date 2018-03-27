package com.chat.server.Translation;

import java.util.Map;

/**
 *
 * @author kari
 */
public abstract class AbstractTranslationClient {

  private String targetLanguage;
  private String sourceLanguage;

    public AbstractTranslationClient(String sourceLanguage ,String targetLanguage ) {
        this.targetLanguage = targetLanguage;
        this.sourceLanguage = sourceLanguage;
    }
    
  abstract String traslateString(String input);
}
