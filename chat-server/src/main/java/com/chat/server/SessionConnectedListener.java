package com.chat.server;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class SessionConnectedListener implements ApplicationListener<SessionConnectedEvent> {

  @Autowired
  private ApplicationContext applicationContext;

  @Override
  public void onApplicationEvent(SessionConnectedEvent e) {
    Map<String, SessionConnectedEvent> sessionConnectedEvents
      = (Map<String, SessionConnectedEvent>) applicationContext.getBean("sessionConnectedEvents");
    sessionConnectedEvents.put((String) e.getMessage().getHeaders().get("simpSessionId"), e);
  }

}
