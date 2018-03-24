package com.chat.server.Translation;

import java.util.Map;

/**
 *
 * @author kari
 */
public abstract class AbstractTranslationClient {

  private String targetLanguage;

  public AbstractTranslationClient(String targetLanguage) {
    this.targetLanguage = targetLanguage;
  }

  public String getTargetLanguage() {
    return targetLanguage;
  }

  public void setTargetLanguage(String targetLanguage) {
    this.targetLanguage = targetLanguage;
  }

  abstract Map<String,String> traslateString(String input);
}
