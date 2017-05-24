package com.greenfox.Modell;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Adam on 2017. 05. 24..
 */
@Getter
@Setter
public class errorStatus extends Status{
  private String status;
  private String error;

  public errorStatus() {
  }

  public errorStatus(String status, String error) {
    this.status = status;
    this.error = error;
  }
}
