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

  public MessageValidator() {
  }

  public boolean checkId(MessageToBroadcast messageToBroadcast) {
    return messageToBroadcast.getMessage().getId() != 0;
  }

  public boolean checkUsername(MessageToBroadcast messageToBroadcast) {
    return messageToBroadcast.getMessage().getUsername() != "null";
  }

  public boolean checkTimeStamp(MessageToBroadcast messageToBroadcast) {
    return messageToBroadcast.getMessage().getTimestamp() != 0;
  }

  public boolean checkText(MessageToBroadcast messageToBroadcast) {
    return messageToBroadcast.getMessage().getText() != "null";
  }

  public boolean checkClient(MessageToBroadcast messageToBroadcast) {
    return messageToBroadcast.getClient().getId() != "null";
  }

  public boolean validateMessage(MessageToBroadcast messageToBroadcast) {
    return checkId(messageToBroadcast) &&
            checkUsername(messageToBroadcast) &&
            checkTimeStamp(messageToBroadcast) &&
            checkText(messageToBroadcast) &&
            checkClient(messageToBroadcast);
  }

}
