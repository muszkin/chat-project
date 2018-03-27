package com.chat.server.Translation;

/**
 *
 * @author kari
 */
public abstract class AbstractDetectorClient {
    
    private String detectedLanguage;

    public String getDetectedLanguage() {
        return detectedLanguage;
    }

    public void setDetectedLanguage(String detectedLanguage) {
        this.detectedLanguage = detectedLanguage;
    }
    
    abstract void detect (String input);
}
