package com.greenfox.Controller;

import com.greenfox.Modell.*;
import com.greenfox.Service.MessageOperator;
import com.greenfox.Service.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Adam on 2017. 05. 19..
 */

@RestController
public class RestMainController {

  @Autowired
  private MessageOperator messageOperator;
  @Autowired
  private MessageValidator messageValidator;

  @CrossOrigin("*")
  @PostMapping("/api/message/receive")
  public Status receiveMessage(@RequestBody MessageToBroadcast messageToBroadcast) {
   if (messageOperator.messageAlreadyExists(messageToBroadcast)) {
     return new okStatus();
   } else {
     if (messageValidator.validateMessage(messageToBroadcast)) {
       messageOperator.saveAndForwardMessage(messageToBroadcast);
       return new okStatus();
     } else {
       return new errorStatus("error", "Missing fields:");
     }
   }
  }

}
