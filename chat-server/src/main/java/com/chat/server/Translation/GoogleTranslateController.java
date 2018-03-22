package com.chat.server.Translation;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 *
 * @author kari
 */
public class GoogleTranslateController extends TranslationInterface {

  private String sourceLanguage;

  public GoogleTranslateController(String sourceLanguage, String targetLanguage) {
    super(targetLanguage);
    this.sourceLanguage = sourceLanguage;
  }

  public String getSourceLanguage() {
    return sourceLanguage;
  }

  public void setSourceLanguage(String sourceLanguage) {
    this.sourceLanguage = sourceLanguage;
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
