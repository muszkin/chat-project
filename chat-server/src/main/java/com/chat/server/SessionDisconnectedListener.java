package com.chat.server;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class SessionDisconnectedListener implements ApplicationListener<SessionDisconnectEvent> {

  @Autowired
  private ApplicationContext applicationContext;

  @Override
  public void onApplicationEvent(SessionDisconnectEvent e) {
    Map<String, SessionConnectedEvent> sessionConnectedEvents
      = (Map<String, SessionConnectedEvent>) applicationContext.getBean("sessionConnectedEvents");
    sessionConnectedEvents.remove(e.getSessionId());
  }

}
