package com.greenfox.Modell;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Adam on 2017. 05. 19..
 */

@Getter
@Setter
public class MessageToBroadcast {
  public Message message;
  public Client client;

  public MessageToBroadcast() {
  }

  public MessageToBroadcast(Client client) {
    this.message = message;
    this.client = client;
  }
}
