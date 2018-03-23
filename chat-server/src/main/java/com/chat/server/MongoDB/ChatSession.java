package com.chat.server.MongoDB;

import com.chat.server.Message.ChatMessage;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat-sessions")
public class ChatSession {

  private String id;
  private String sessionId;
  private List<ChatMessage> messages = new LinkedList<>();

  public ChatSession(String sessionId) {
    this.sessionId = sessionId;
  }

  private ChatSession() {
  }

  public String getId() {
    return id;
  }

  public String getSessionId() {
    return sessionId;
  }

  public List<ChatMessage> getMessages() {
    return Collections.unmodifiableList(messages);
  }

  public void addToList(ChatMessage chatMessage) {
    messages.add(chatMessage);
  }

}
