package com.chat.server.Controller;

import com.chat.server.MongoDB.MongoDBClient;
import com.chat.server.MongoDB.UserMessages;
import com.google.gson.Gson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userMessages")
public class UserMessagesController {
  
  @Autowired
  private MongoDBClient mongoDBClient;
  private final Gson gson = new Gson();
  
  @RequestMapping("/all")
  public String getAllUserMessages(@RequestParam("userId") String userUd) {
    List<UserMessages> list = mongoDBClient.getAllUserMessages();
    list.sort((m, n) -> m.getLastMessageDate().compareTo(n.getLastMessageDate()));
    return gson.toJson(list);
  }
  
}
