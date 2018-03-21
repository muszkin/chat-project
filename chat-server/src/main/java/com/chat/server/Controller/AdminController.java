package com.chat.server.Controller;

import com.chat.server.Message.ChatMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private SimpMessageSendingOperations messageSendingOperations;

  @RequestMapping("/sendToAllConnected")
  public String sendToAllConnected(@RequestParam("message") String message) {
    List<SessionConnectedEvent> sessionConnectedEvents
      = (List<SessionConnectedEvent>) applicationContext.getBean("sessionConnectedEvents");
    sessionConnectedEvents.forEach(event -> {
      String sessionId = (String) event.getMessage().getHeaders().get("simpSessionId");
      messageSendingOperations.convertAndSend("/topic/private/" + sessionId, new ChatMessage(message));
    });
    return "OK";
  }

  @RequestMapping("/sendToUser")
  public String sendToUser(@RequestParam("id") String sessionId, @RequestParam("message") String message) {
    messageSendingOperations.convertAndSend("/topic/private/" + sessionId, new ChatMessage(message));
    return "OK";
  }

}
