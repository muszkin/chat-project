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
public class ChatController {

  @Autowired
  private SimpMessageSendingOperations messageSendingOperations;
  @Autowired
  private MongoDBClient mongoDBClient;

  @MessageMapping("/hello")
  public void hello(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
    Thread.sleep(100); // simulated delay
    messageSendingOperations.convertAndSend("/topic/private/" + headerAccessor.getSessionId(), new ChatMessage("Hello, " + message.getName() + "!"));
  }

  @MessageMapping("/chat")
  public void chat(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
    Thread.sleep(100); // simulated delay

// auth problem - zmienna systemowa jest pusta :C
//        GoogleTranslateController polishToEnglishTranslator = new GoogleTranslateController("pl", "en");
//        String transaltedByGoogle = polishToEnglishTranslator.traslateString(message.getContent());
//        messageSendingOperations.convertAndSend("/topic/private/" + headerAccessor.getSessionId(), new ChatMessage("<b>You</b>: " + message.getContent() + transaltedByGoogle));
    YandexTransalteClient translator = new YandexTransalteClient("en");
    ChatMessage chatMessage = new ChatMessage("<b>You</b>: " + message.getContent() + " [" + translator.traslateString(message.getContent()) + "]");
    messageSendingOperations.convertAndSend("/topic/private/" + headerAccessor.getSessionId(), chatMessage);
    mongoDBClient.addNewChatMessageToChatSession(headerAccessor.getSessionId(), chatMessage);
  }

}
