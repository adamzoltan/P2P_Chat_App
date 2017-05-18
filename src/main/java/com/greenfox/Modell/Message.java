package com.greenfox.Modell;

import com.greenfox.Repository.MessageRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Adam on 2017. 05. 18..
 */
@Entity
@Getter
@Setter
public class Message {

  @Id
  private long id;
  private String userName;
  private String text;

  public Message() {
  }

  public Message(String userName, String text) {
    this.id = generateId();
    this.userName = userName;
    this.text = text;
  }

  public long generateId() {
    long id = 1000000 + (long)(Math.random()* 8999999);
    return id;
  }
}
