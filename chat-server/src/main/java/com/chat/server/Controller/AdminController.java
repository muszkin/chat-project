package com.chat.server.Controller;

import com.chat.server.Message.ChatMessage;
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
    private SimpMessageSendingOperations messageSendingOperations;

//  public String sendToAllConnected(@RequestParam("message") String message) {
//    List<SessionConnectedEvent> sessionConnectedEvents
//      = (List<SessionConnectedEvent>) applicationContext.getBean("sessionConnectedEvents");
//    sessionConnectedEvents.forEach(event -> {
//      String sessionId = (String) event.getMessage().getHeaders().get("simpSessionId");
//      messageSendingOperations.convertAndSend("/topic/private/" + sessionId, new ChatMessage(message));
//    });
//    return "OK";
//  }
    @MessageMapping("/admin")
    public void sendToUser(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        //pobierz info z mongo
        
        String adminSourceLang = "pl";
        String userTargetLang = "it";

        //tlumaczymy
        YandexTransalteClient translator = new YandexTransalteClient(userTargetLang, adminSourceLang);
        message.setTranslatedContend(translator.traslateString(message.getContent()));
        messageSendingOperations.convertAndSend("/topic/private/" + headerAccessor.getNativeHeader("user-id").get(0), message);
    }

}
