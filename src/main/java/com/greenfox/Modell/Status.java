package com.greenfox.Modell;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Adam on 2017. 05. 19..
 */
@Getter
@Setter
public class Status {
  private String status;
  private String message;

  public Status(String status) {
    this.status = status;
  }

  public Status(String status, String message) {
    this.status = status;
    this.message = message;
  }

}
