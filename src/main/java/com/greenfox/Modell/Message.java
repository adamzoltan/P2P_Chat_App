package com.greenfox.Modell;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;



/**
 * Created by Adam on 2017. 05. 18..
 */
@Component
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
    this.username = username;
    this.text = text;
    this.timestamp = timeStamp();
  }

  private long timeStamp() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    return timestamp.getTime();
  }

}
