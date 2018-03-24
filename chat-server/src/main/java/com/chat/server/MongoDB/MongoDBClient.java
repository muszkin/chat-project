package com.chat.server.MongoDB;

import com.chat.server.Message.ChatMessage;
import java.util.List;
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

  public void addNewUserMessages(UserMessages userMessages) {
    chatSessionRepository.save(userMessages);
  }

  public void addNewChatMessageToUserMessages(String userId, ChatMessage chatMessage) {
    ExampleMatcher matcher = ExampleMatcher.matching()
      .withIgnoreNullValues()
      .withIgnorePaths("messages")
      .withIgnorePaths("lastMessageDate")
      .withIgnorePaths("id");
    Optional<UserMessages> chatSession = chatSessionRepository.findOne(
      Example.of(new UserMessages(userId), matcher));
    if (chatSession.isPresent()) {
      chatSession.get().addToList(chatMessage);
      chatSessionRepository.save(chatSession.get());
    }
  }
  
  public List<UserMessages> getAllUserMessages() {
    return chatSessionRepository.findAll();
  }

}
