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
  Client client;


  public MessageOperator() {
  }

  public void saveAndBroadcastMessage(String message) {
    Message newMessage = new Message(userRepository.findOne(1).getName(),message);
    while(messageRepository.exists(newMessage.getId())) {
      newMessage = new Message(userRepository.findOne(1).getName(),message);
    }
    if(!message.isEmpty()) {
      messageRepository.save(newMessage);
    }
    ReceivedMessage receivedMessage = new ReceivedMessage();
    client.setId(System.getenv("CHAT_APP_UNIQUE_ID"));
    receivedMessage.setMessage(newMessage);
    receivedMessage.setClient(client);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForLocation(System.getenv("CHAT_APP_PEER_ADDRESS"), receivedMessage);
  }

  public void forwardMessage(ReceivedMessage receivedMessage) {
    Message message = new Message();
    message.createMessage(receivedMessage);
    messageRepository.save(message);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForLocation(System.getenv("CHAT_APP_PEER_ADDRESS"), receivedMessage);

  }

}
