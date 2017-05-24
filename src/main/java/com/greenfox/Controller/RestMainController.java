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
  MessageOperator messageOperator;
  @Autowired
  MessageValidator messageValidator;

  @CrossOrigin("*")
  @PostMapping("/api/message/receive")
  public Status receiveMessage(@RequestBody MessageToBroadcast messageToBroadcast) {
   if(messageOperator.messageAlreadyExists(messageToBroadcast)) {
     Status ok = new okStatus("ok");
     return ok;
   } else {
     if (messageValidator.validateMessage(messageToBroadcast)) {
       Status ok = new okStatus("ok");
       messageOperator.saveAndForwardMessage(messageToBroadcast);
       return ok;
     } else {
       Status error = new errorStatus("ok", "Missing fields:");
       return error;
     }
   }
  }

}
