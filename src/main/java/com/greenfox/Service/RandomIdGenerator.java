package com.greenfox.Service;

import com.greenfox.Modell.Message;
import com.greenfox.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2017. 05. 24..
 */
@Service
public class RandomIdGenerator {

  @Autowired
  private MessageRepository messageRepository;

  public RandomIdGenerator() {
  }

  public void generateId(Message message) {
    long id;
    do {
      id = (long) (1000000 + (Math.random() * 8999999));
    } while (messageRepository.exists(id));
    message.setId(id);
  }

}
