package com.chat.server.Controller;

import com.chat.server.Message.ChatMessage;
import com.chat.server.MongoDB.MongoDBClient;
import com.chat.server.Translation.YandexTransalteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {

  @Autowired
  private ApplicationContext applicationContext;
  @Autowired
  private MongoDBClient mongoDBClient;
  @Autowired
  private SimpMessageSendingOperations messageSendingOperations;

  @MessageMapping("/admin")
  public void sendToUser(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
    //pobierz info z mongo

    String adminSourceLang = "pl";
    String userTargetLang = "it";

    YandexTransalteClient translator = new YandexTransalteClient(userTargetLang, adminSourceLang);
    message.setContent(translator.traslateString(message.getContent()));
    message.setUserIsAdmin();
    String userId = headerAccessor.getNativeHeader("user-id").get(0);
    messageSendingOperations.convertAndSend("/topic/private/" + userId, message);
    mongoDBClient.addNewChatMessageToUserMessages(userId, message);
  }

}
