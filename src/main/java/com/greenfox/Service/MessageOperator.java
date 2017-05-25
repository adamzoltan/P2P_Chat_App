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
  private MessageRepository messageRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RandomIdGenerator randomIdGenerator;

  public MessageOperator() {
  }

  public void saveAndBroadcastMessage(String message) {
    Message newMessage = new Message(userRepository.findOne(1).getName(), message);
    randomIdGenerator.generateId(newMessage);
    messageRepository.save(newMessage);

    Client client = new Client(System.getenv("CHAT_APP_UNIQUE_ID"));

    MessageToBroadcast messageToBroadcast = new MessageToBroadcast();
    messageToBroadcast.setClient(client);
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
    return messageRepository.exists(messageToBroadcast.getMessage().getId());
  }

}
