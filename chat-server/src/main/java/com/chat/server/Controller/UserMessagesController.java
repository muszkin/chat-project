package com.chat.server.Controller;

import com.chat.server.MongoDB.MongoDBClient;
import com.chat.server.MongoDB.UserMessages;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@RestController
@RequestMapping("/userMessages")
public class UserMessagesController {

  @Autowired
  private MongoDBClient mongoDBClient;
  @Autowired
  private ApplicationContext applicationContext;
  private final Gson gson = new Gson();

  @RequestMapping("/all")
  public String getAllUserMessages() {
    List<UserMessages> list = mongoDBClient.getAllUserMessages();
    list.sort((m, n) -> m.getLastMessageDate().compareTo(n.getLastMessageDate()));
    return gson.toJson(list);
  }

  @RequestMapping("/allActive")
  public String getAllActiveUserMessages() {
    Map<String, SessionConnectedEvent> sessionConnectedEvents
      = (Map<String, SessionConnectedEvent>) applicationContext.getBean("sessionConnectedEvents");
    Set<String> userIds = sessionConnectedEvents.keySet();
    List<UserMessages> list = mongoDBClient.getAllUserMessages(userIds);
    list.sort((m, n) -> m.getLastMessageDate().compareTo(n.getLastMessageDate()));
    return gson.toJson(list);
  }

  @RequestMapping("/single")
  public String getSingleUserMessages(@RequestParam("userId") String userId) {
    Optional<UserMessages> userMessages = mongoDBClient.getSingleUserMessages(userId);
    return gson.toJson(userMessages.isPresent() ? userMessages.get() : null);
  }

}
