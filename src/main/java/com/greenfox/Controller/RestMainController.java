package com.greenfox.Controller;

import com.greenfox.Modell.Message;
import com.greenfox.Modell.ReceivedMessage;
import com.greenfox.Modell.Status;
import com.greenfox.Repository.MessageRepository;
import com.greenfox.Service.MessageOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Adam on 2017. 05. 19..
 */

@RestController
public class RestMainController {

  @Autowired
  MessageRepository messageRepository;
  @Autowired
  MessageOperator messageOperator;


  @PostMapping("/api/message/receive")
  @CrossOrigin("*")
  public Status receiveMessage(@RequestBody ReceivedMessage receivedMessage) {
    messageOperator.forwardMessage(receivedMessage);
    Status status = new Status();
    status.setStatus("ok");
    return status;
  }
}
