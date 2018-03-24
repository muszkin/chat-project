package com.chat.server.MongoDB;

import com.chat.server.Message.ChatMessage;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    Optional<UserMessages> userMessages = getSingleUserMessages(userId);
    if (userMessages.isPresent()) {
      userMessages.get().addToList(chatMessage);
      chatSessionRepository.save(userMessages.get());
    }
  }

  public List<UserMessages> getAllUserMessages() {
        return chatSessionRepository.findAll();
  }

  public List<UserMessages> getAllUserMessages(Set<String> userIds) {
    return (List<UserMessages>) chatSessionRepository.findAllById(userIds);
  }

  public Optional<UserMessages> getSingleUserMessages(String userId) {
    ExampleMatcher matcher = ExampleMatcher.matching()
      .withIgnoreNullValues()
      .withIgnorePaths("messages")
      .withIgnorePaths("lastMessageDate")
      .withIgnorePaths("id");
    Optional<UserMessages> userMessages = chatSessionRepository.findOne(
      Example.of(new UserMessages(userId), matcher));
    return userMessages;

  }

  public void setUserLanguage(String userId, String language) {
//    chatSessionRepository
      Optional<UserMessages> userMessage = getSingleUserMessages(userId);
      userMessage.get().setLanguage(language);
      chatSessionRepository.save(userMessage.get());
  }

}
