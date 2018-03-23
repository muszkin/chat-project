package com.chat.server;

import com.chat.server.MongoDB.ChatSession;
import com.chat.server.MongoDB.MongoDBClient;
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
  @Autowired
  private MongoDBClient mongoDBClient;

  @Override
  public void onApplicationEvent(SessionConnectedEvent e) {
    String sessionId = (String) e.getMessage().getHeaders().get("simpSessionId");
    Map<String, SessionConnectedEvent> sessionConnectedEvents
      = (Map<String, SessionConnectedEvent>) applicationContext.getBean("sessionConnectedEvents");
    sessionConnectedEvents.put(sessionId, e);
    mongoDBClient.addNewChatSession(new ChatSession(sessionId));
  }

}
