package com.chat.server.Controller;

import com.chat.server.Message.ChatMessage;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.util.MimeType;
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
      messageSendingOperations.convertAndSendToUser(sessionId, "/topic/private", new ChatMessage(message), createHeaders(sessionId));
    });
    return "OK";
  }

  @RequestMapping("/sendToAllUser")
  public ChatMessage sendToUser(@RequestParam("id") String sessionId, @RequestParam("message") String message) {
    return new ChatMessage(message);
  }

  private Map<String, Object> createHeaders(String sessionId) {
    SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
    accessor.setSessionId(sessionId);
    accessor.setDestination("/topic/private");
    accessor.setContentType(MimeType.valueOf("application/json;charset=UTF-8"));
    accessor.setSubscriptionId("sub-0");
    accessor.setLeaveMutable(true);
    return accessor.getMessageHeaders();
  }

}
