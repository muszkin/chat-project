package com.chat.server.MongoDB;

import com.chat.server.Message.ChatMessage;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userMessages")
public class UserMessages {

  private String id;
  private String userId;
  private Timestamp lastMessageDate;
  private final Map<ChatMessage, Timestamp> messages = new HashMap<>();

  public UserMessages(String userId) {
    this.userId = userId;
  }

  private UserMessages() {
  }

  public String getUserId() {
    return userId;
  }

  public Timestamp getLastMessageDate() {
    return lastMessageDate;
  }

  public Map<ChatMessage, Timestamp> getMessages() {
    return Collections.unmodifiableMap(messages);
  }

  public void addToList(ChatMessage chatMessage) {
    Timestamp date = new Timestamp(System.currentTimeMillis());
    messages.put(chatMessage, date);
    lastMessageDate = date;
  }

}
