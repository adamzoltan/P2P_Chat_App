package com.greenfox.Controller;

import com.greenfox.Modell.Message;
import com.greenfox.Modell.ReceivedMessage;
import com.greenfox.Modell.Status;
import com.greenfox.Repository.MessageRepository;
import com.greenfox.Service.MessageOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Adam on 2017. 05. 19..
 */

@RestController
public class RestMainController {

  @Autowired
  MessageOperator messageOperator;
  @Autowired
  MessageRepository messageRepository;

  @PostMapping("/api/message/receive")
  @CrossOrigin("*")
  public Status receiveMessage(@RequestBody ReceivedMessage receivedMessage) {
    Status status = new Status();
    status.setStatus("ok");
    Message message = new Message();
    message.createMessage(receivedMessage);
    messageRepository.save(message);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForLocation(System.getenv("CHAT_APP_PEER_ADDRESS"), receivedMessage);

    return status;
  }

}
