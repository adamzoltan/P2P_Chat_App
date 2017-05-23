package com.greenfox.Modell;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Adam on 2017. 05. 19..
 */

@Getter
@Setter
public class ReceivedMessage {
  public Message message;
  public Client client;

}
