package com.chat.server.MongoDB;

import com.chat.server.Message.ChatMessage;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

  public void addNewChatMessageToChatSession(String sessionId, ChatMessage chatMessage) {
    ExampleMatcher matcher = ExampleMatcher.matching()
      .withIgnoreNullValues()
      .withIgnorePaths("messages")
      .withIgnorePaths("id");
    Optional<ChatSession> chatSession = chatSessionRepository.findOne(
      Example.of(new ChatSession(sessionId), matcher));
    if (chatSession.isPresent()) {
      chatSession.get().addToList(chatMessage);
      chatSessionRepository.save(chatSession.get());
    }
  }

}
