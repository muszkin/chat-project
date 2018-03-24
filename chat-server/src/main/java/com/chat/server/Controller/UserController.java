package com.chat.server.Controller;

import com.chat.server.Translation.YandexTransalteClient;
import com.chat.server.Message.ChatMessage;
import com.chat.server.Message.HelloMessage;
import com.chat.server.MongoDB.MongoDBClient;
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
    Thread.sleep(100); // simulated delay

    String userSourceLang = "it";
    String adminTargetLang = "pl";
//        String userBrowserLang = headerAccessor.getNativeHeader("browser-lang").get(0);

    YandexTransalteClient translator = new YandexTransalteClient(adminTargetLang, userSourceLang);
    ChatMessage chatMessage = new ChatMessage(translator.traslateString(message.getContent()), headerAccessor.getNativeHeader("user-id").get(0));
    messageSendingOperations.convertAndSend("/topic/admin", chatMessage);
    mongoDBClient.addNewChatMessageToUserMessages(headerAccessor.getNativeHeader("user-id").get(0), chatMessage);
  }

}
