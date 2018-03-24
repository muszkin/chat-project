package com.chat.server.Message;

public class ChatMessage {

  private String content;
  private String userId;

  public ChatMessage() {
  }

  public ChatMessage(String content, String userId) {
    this.content = content;
    this.userId = userId;
  }


  public String getContent() {
    return content;
  }

  public String getUserId() {
    return userId;
  }
  

}
