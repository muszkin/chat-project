package com.chat.server;

import com.chat.server.MongoDB.UserMessages;
import com.chat.server.MongoDB.MongoDBClient;
import java.util.LinkedList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.support.GenericMessage;
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

    GenericMessage headers = (GenericMessage) e.getMessage().getHeaders().get("simpConnectMessage");
    Map<String, LinkedList> nativeHeaders = (Map<String, LinkedList>) headers.getHeaders().get("nativeHeaders");
    String userId = (String) nativeHeaders.get("user-id").get(0);

    Map<String, SessionConnectedEvent> sessionConnectedEvents
      = (Map<String, SessionConnectedEvent>) applicationContext.getBean("sessionConnectedEvents");
    if (sessionConnectedEvents.put(userId, e) == null) {
      mongoDBClient.addNewUserMessages(new UserMessages(userId));
    }
  }

}
