package com.chat.server;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class ApplicationContextBean {

  private final Map<String, SessionConnectedEvent> sessionConnectedEvents = new HashMap<>();

  @Bean
  @Scope("singleton")
  public Map<String, SessionConnectedEvent> sessionConnectedEvents() {
    return sessionConnectedEvents;
  }
}
