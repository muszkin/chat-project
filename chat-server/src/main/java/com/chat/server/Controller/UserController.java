package com.chat.server.Controller;

import com.chat.server.Translation.YandexTransalteClient;
import com.chat.server.Message.ChatMessage;
import com.chat.server.Message.HelloMessage;
import com.chat.server.MongoDB.MongoDBClient;
import com.chat.server.MongoDB.UserMessages;
import com.chat.server.Translation.YandexDetectorClient;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

  @Autowired
  private SimpMessageSendingOperations messageSendingOperations;
  @Autowired
  private MongoDBClient mongoDBClient;

  @MessageMapping("/hello")
  public void hello(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
    Thread.sleep(100); // simulated delay
//    messageSendingOperations.convertAndSend("/topic/private/" + headerAccessor.getHeader("user-id"), new ChatMessage("Hello, " + message.getName() + "!"));
  }

  @MessageMapping("/chat")
  public void chat(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
//    Thread.sleep(100); // simulated delay

    String adminTargetLang = "pl";
    String userId = headerAccessor.getNativeHeader("user-id").get(0);
    Optional<UserMessages> userMessages = mongoDBClient.getSingleUserMessages(userId);
    YandexTransalteClient translator = null;
    YandexDetectorClient langDetector = new YandexDetectorClient();
    String translatedMessage = null;
    

    if (userMessages.isPresent() && userMessages.get().getLanguage() != null) {
//      pozyskiwanie z przegladarki dla potwierdzenia kiedys?
//    String userBrowserLang = headerAccessor.getNativeHeader("browser-lang").get(0);
      
      String userLanguage = userMessages.get().getLanguage();
      translator = new YandexTransalteClient(userLanguage, adminTargetLang);
      translatedMessage = translator.traslateString(message.getContent());
    } else {
      langDetector.detect(message.getContent());
      translator = new YandexTransalteClient(langDetector.getDetectedLanguage(), adminTargetLang);
      translatedMessage = translator.traslateString(message.getContent());
      mongoDBClient.setUserLanguage(userId, langDetector.getDetectedLanguage());
    }

    ChatMessage chatMessage = new ChatMessage(
      translatedMessage,
      userId,
      System.currentTimeMillis());
    messageSendingOperations.convertAndSend("/topic/admin", chatMessage);
    mongoDBClient.addNewChatMessageToUserMessages(userId, chatMessage);
  }

}
