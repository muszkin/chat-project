/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.server.Translation;

/**
 *
 * @author kari
 */
public abstract class TranslationInterface {

  private String targetLanguage;

  public TranslationInterface(String targetLanguage) {
    this.targetLanguage = targetLanguage;
  }

  public String getTargetLanguage() {
    return targetLanguage;
  }

  public void setTargetLanguage(String targetLanguage) {
    this.targetLanguage = targetLanguage;
  }

  abstract String traslateString(String input);
}
