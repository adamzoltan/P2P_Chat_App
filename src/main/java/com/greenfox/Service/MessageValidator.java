package com.greenfox.Service;

import com.greenfox.Modell.MessageToBroadcast;
import com.greenfox.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2017. 05. 24..
 */
@Service
public class MessageValidator {

  @Autowired
  MessageRepository messageRepository;

  public MessageValidator() {
  }

  public boolean checkId(MessageToBroadcast messageToBroadcast) {
    if(messageToBroadcast.getMessage().getId() != 0) {
      return true;
    } else return false;
  }

  public boolean checkUsername(MessageToBroadcast messageToBroadcast) {
    if(messageToBroadcast.getMessage().getUsername() != "null") {
      return true;
    } else return false;
  }

  public boolean checkTimeStamp(MessageToBroadcast messageToBroadcast) {
    if(messageToBroadcast.getMessage().getTimestamp() != 0) {
      return true;
    } else return false;
  }

  public boolean checkText(MessageToBroadcast messageToBroadcast) {
    if(messageToBroadcast.getMessage().getText() != "null") {
      return true;
    } else return false;
  }

  public boolean checkClient(MessageToBroadcast messageToBroadcast) {
    if(messageToBroadcast.getClient().getId() != "null") {
      return true;
    } else return false;
  }
  public boolean validateMessage(MessageToBroadcast messageToBroadcast) {
    if(checkId(messageToBroadcast) && checkUsername(messageToBroadcast) && checkTimeStamp(messageToBroadcast) && checkText(messageToBroadcast) && checkClient(messageToBroadcast)) {
      return true;
    } else return false;
  }
}
