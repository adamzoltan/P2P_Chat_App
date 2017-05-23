package com.greenfox.Modell;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by Adam on 2017. 05. 19..
 */
@Component
@Getter
@Setter
public class Client {
  private String id;

  public Client() {
  }
}
