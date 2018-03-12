package com.chat.server.Controller;

import com.chat.server.Message.GreetingMessage;
import com.chat.server.Message.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

  @MessageMapping("/hello")
  @SendTo("/topic/private")
  public GreetingMessage hello(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new GreetingMessage("Hello, " + message.getName() + "!");
  }

  @MessageMapping("/chat")
  @SendTo("/topic/private")
  public GreetingMessage chat(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new GreetingMessage("You said: " + message.getName());
  }

}
