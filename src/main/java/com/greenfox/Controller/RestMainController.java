package com.greenfox.Controller;

import com.greenfox.Modell.Message;
import com.greenfox.Modell.ReceivedMessage;
import com.greenfox.Modell.Status;
import com.greenfox.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Adam on 2017. 05. 19..
 */

@RestController
public class RestMainController {

  @Autowired
  MessageRepository messageRepository;

  @PostMapping("/api/message/receive")
  @CrossOrigin("*")
  public Status receiveMessage(@RequestBody ReceivedMessage receivedMessage) {
    Message message = new Message();
    Status okStatus = new Status("ok");
    message.createMessage(receivedMessage);
    messageRepository.save(message);
    return okStatus ;
  }
}
