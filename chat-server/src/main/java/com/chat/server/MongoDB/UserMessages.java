package com.chat.server.MongoDB;

import com.chat.server.Message.ChatMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userMessages")
public class UserMessages {

  private String id;
  private String userId;
  private Long lastMessageDate;
  private final List<ChatMessage> messages = new ArrayList<>();
  private String language;

  public UserMessages(String userId) {
    this.userId = userId;
  }

  private UserMessages() {
  }

  public String getUserId() {
    return userId;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getLanguage() {
    return language;
  }

  public Long getLastMessageDate() {
    return lastMessageDate;
  }

  public List<ChatMessage> getMessages() {
    return Collections.unmodifiableList(messages);
  }

  public void addToList(ChatMessage chatMessage) {
    Long date = System.currentTimeMillis();
    messages.add(chatMessage);
    lastMessageDate = date;
  }

}
