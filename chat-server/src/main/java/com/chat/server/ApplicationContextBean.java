package com.chat.server;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class ApplicationContextBean {

  private final List<SessionConnectedEvent> sessionConnectedEvents = new ArrayList<>();

  @Bean
  @Scope("singleton")
  public List<SessionConnectedEvent> sessionConnectedEvents() {
    return sessionConnectedEvents;
  }
}
