package com.chat.server.MongoDB;

import com.chat.server.Message.ChatMessage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userMessages")
public class UserMessages {

  private String id;
  private String userId;
  private Long lastMessageDate;
  private final Map<Long, ChatMessage> messages = new HashMap<>();

  public UserMessages(String userId) {
    this.userId = userId;
  }

  private UserMessages() {
  }

  public String getUserId() {
    return userId;
  }

  public Long getLastMessageDate() {
    return lastMessageDate;
  }

  public Map<Long, ChatMessage> getMessages() {
    return Collections.unmodifiableMap(messages);
  }

  public void addToList(ChatMessage chatMessage) {
    Long date = System.currentTimeMillis();
    messages.put(date, chatMessage);
    lastMessageDate = date;
  }

}
