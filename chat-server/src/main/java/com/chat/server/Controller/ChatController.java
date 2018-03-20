package com.chat.server.Controller;

import com.chat.server.Message.ChatMessage;
import com.chat.server.Message.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

  @MessageMapping("/hello")
  @SendTo("/topic/private")
  public ChatMessage hello(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
    Thread.sleep(100); // simulated delay
    System.out.print(headerAccessor.getSessionAttributes());
    return new ChatMessage("Hello, " + message.getName() + "!");
  }

  @MessageMapping("/chat")
  @SendTo("/topic/private")
  public ChatMessage chat(ChatMessage message) throws Exception {
    Thread.sleep(100); // simulated delay
    return new ChatMessage("You said: " + message.getContent());
  }

}
