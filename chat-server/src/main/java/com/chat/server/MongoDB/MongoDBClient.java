package com.chat.server.MongoDB;

import com.chat.server.Message.ChatMessage;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MongoDBClient implements CommandLineRunner {

  private final ChatSessionRepository chatSessionRepository;

  public MongoDBClient(ChatSessionRepository chatSessionRepository) {
    this.chatSessionRepository = chatSessionRepository;
  }

  @Override
  public void run(String... strings) throws Exception {
  }
  
  public void addNewChatSession(ChatSession chatSession) {
    chatSessionRepository.save(chatSession);
  }
  
  public void addNewChatMessageToChatSession(String id, ChatMessage chatMessage) {
    Optional<ChatSession> chatSession = chatSessionRepository.findById(id);
    if(chatSession.isPresent()) {
      chatSession.get().addToList(chatMessage);
      chatSessionRepository.save(chatSession.get());
    }
  }

}
