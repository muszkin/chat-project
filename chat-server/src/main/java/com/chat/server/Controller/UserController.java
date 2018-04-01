package com.chat.server.Controller;

import com.chat.server.Translation.YandexTransalteClient;
import com.chat.server.Message.ChatMessage;
import com.chat.server.Message.HelloMessage;
import com.chat.server.MongoDB.MongoDBClient;
import com.chat.server.MongoDB.UserMessages;
import com.chat.server.Translation.AbstractDetectorClient;
import com.chat.server.Translation.YandexDetectorClient;
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
  private final AbstractDetectorClient langDetector =  new YandexDetectorClient();

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
    if (headerAccessor.getNativeHeader("user-id").get(0) == null) throw new IllegalStateException();
    
    Optional<UserMessages> userMessages = mongoDBClient.getSingleUserMessages(userId);
    YandexTransalteClient translator = null;
    String translatedMessage = null;
    

    if (userMessages.isPresent() && userMessages.get().getLanguage() != null) {
//      pozyskiwanie z przegladarki dla potwierdzenia kiedys?
//    String userBrowserLang = headerAccessor.getNativeHeader("browser-lang").get(0);
      
      translator = new YandexTransalteClient(userMessages.get().getLanguage(), adminTargetLang);
      translatedMessage = translator.traslateString(message.getContent());
    } else {
      this.langDetector.detect(message.getContent());
      translator = new YandexTransalteClient(this.langDetector.getDetectedLanguage(), adminTargetLang);
      translatedMessage = translator.traslateString(message.getContent());
      mongoDBClient.setUserLanguage(userId, this.langDetector.getDetectedLanguage());
    }

    ChatMessage chatMessage = new ChatMessage(
      translatedMessage,
      userId,
      System.currentTimeMillis());
    messageSendingOperations.convertAndSend("/topic/admin", chatMessage);
    mongoDBClient.addNewChatMessageToUserMessages(userId, chatMessage);
  }

}
