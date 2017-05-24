package com.greenfox.Modell;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Adam on 2017. 05. 22..
 */
@Getter
@Setter
public class okStatus extends Status{
  private String status;

  public okStatus() {
  }

  public okStatus(String status) {
    this.status = status;
  }
}
