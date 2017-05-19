package com.greenfox.Modell;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Adam on 2017. 05. 18..
 */
@Entity
@Getter
@Setter
public class Message {

  @Id
  private long id;
  private String username;
  private String text;
  private long timestamp;


  public Message() {
  }

  public Message(String username, String text) {
    this.id = generateId();
    this.username = username;
    this.text = text;
    this.timestamp = timeStamp();
  }

  public long generateId() {
    long id = 1000000 + (long)(Math.random()* 8999999);
    return id;
  }

  public long timeStamp() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    return timestamp.getTime();
  }

  public void createMessage(ReceivedMessage receivedMessage) {
    this.setId(receivedMessage.getMessage().getId());
    this.setUsername(receivedMessage.getMessage().getUsername());
    this.setText(receivedMessage.getMessage().getText());
    this.setTimestamp(receivedMessage.getMessage().getTimestamp());
  }
}
