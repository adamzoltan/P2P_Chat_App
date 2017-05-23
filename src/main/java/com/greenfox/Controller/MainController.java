package com.greenfox.Controller;

import com.greenfox.Modell.Message;
import com.greenfox.Modell.User;
import com.greenfox.Repository.MessageRepository;
import com.greenfox.Repository.UserRepository;
import com.greenfox.Service.MessageOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Adam on 2017. 05. 17..
 */

@Controller
public class MainController {

  Message message = new Message("App", "Hi there! Submit your message using the send button!");
  String missingUserName = "";
  @Autowired
  UserRepository userRepository;
  @Autowired
  MessageRepository messageRepository;
  @Autowired
  MessageOperator messageOperator;

  @RequestMapping("/")
  public String index(Model model) {
    messageRepository.save(message);
    model.addAttribute("messages", messageRepository.findAllByOrderByTimestampAsc());
    model.addAttribute("user", userRepository.findOne(1));
    model.addAttribute("error", missingUserName);
    if(userRepository.count() == 0) {
      return "redirect:/enter";
    } else {
      return "index";
    }
  }

  @RequestMapping("/enter")
  public String enter(Model model) {
    model.addAttribute("error", missingUserName);
    if(userRepository.count() > 0) {
      return "redirect:/";
    } else {
      return "enter";
    }
  }

  @RequestMapping("/enterUser")
  public String addUser(@RequestParam(name = "name", required = false) String name) {
    if (name.isEmpty()) {
      missingUserName = "The username field is empty";
      return "redirect:/enter";
    } else {
      userRepository.save(new User(name));
      missingUserName = "";
      return "redirect:/";
    }
  }

  @RequestMapping("/updateUser")
  public String updateUser(@RequestParam(name = "name", required = false) String name) {
    if(name.isEmpty()) {
      missingUserName = "The username field is empty";
      return "redirect:/";
    } else {
      User newUser = userRepository.findOne(1);
      newUser.setName(name);
      userRepository.save(newUser);
      missingUserName = "";
      return "redirect:/";
    }
  }

  @RequestMapping("/newMessage")
  public String newMessage(@RequestParam(name = "message")String message) {
    messageOperator.saveAndBroadcastMessage(message);
    return "redirect:/";
  }

}
