package com.greenfox.Service;

import com.greenfox.Modell.*;
import com.greenfox.Repository.MessageRepository;
import com.greenfox.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Adam on 2017. 05. 23..
 */

@Service
public class MessageOperator {

  @Autowired
  MessageRepository messageRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RandomIdGenerator randomIdGenerator;

  public MessageOperator() {
  }

  public void saveAndBroadcastMessage(String message) {
    Message newMessage = new Message(userRepository.findOne(1).getName(),message);
    randomIdGenerator.generateId(newMessage);
    messageRepository.save(newMessage);
    MessageToBroadcast messageToBroadcast = new MessageToBroadcast();
    messageToBroadcast.getClient().setId(System.getenv("CHAT_APP_UNIQUE_ID"));
    messageToBroadcast.setMessage(newMessage);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForLocation(System.getenv("CHAT_APP_PEER_ADDRESS"), messageToBroadcast);
  }

  public void saveAndForwardMessage(MessageToBroadcast messageToBroadcast) {
    Message newMessage = messageToBroadcast.getMessage();
    messageRepository.save(newMessage);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForLocation(System.getenv("CHAT_APP_PEER_ADDRESS"), messageToBroadcast);
  }

  public boolean messageAlreadyExists(MessageToBroadcast messageToBroadcast) {
    if (messageRepository.exists(messageToBroadcast.getMessage().getId())) {
      return true;
    } else return false;
  }
}
