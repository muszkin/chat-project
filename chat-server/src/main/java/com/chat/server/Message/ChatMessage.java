package com.chat.server.Message;

public class ChatMessage {

  private String content;
  private String userId;
  private Long date;

  public ChatMessage() {
  }

  public ChatMessage(String content, String userId, Long date) {
    this.content = content;
    this.userId = userId;
    this.date = date;
  }

  public String getContent() {
    return content;
  }

  public String getUserId() {
    return userId;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setUserIsAdmin() {
    this.userId = "admin";
  }

  public Long getDate() {
    return date;
  }

  public void setDate(Long date) {
    this.date = date;
  }

}
