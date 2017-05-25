package com.greenfox.Modell;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Adam on 2017. 05. 25..
 */

@Getter
@Setter
public class MessageExistsStatus extends Status {

    private String status = "message exists";

    public MessageExistsStatus() {
    }

  }

