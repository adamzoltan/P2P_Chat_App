package com.greenfox.Service;

import com.greenfox.Modell.User;
import com.greenfox.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2017. 05. 24..
 */
@Service
public class UserOperator {

  @Autowired
  private UserRepository userRepository;

  public UserOperator() {
  }

  public void updateUser(String name) {
    User newUser = userRepository.findOne(1);
    newUser.setName(name);
    userRepository.save(newUser);
  }

}
