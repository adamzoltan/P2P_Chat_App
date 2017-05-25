package com.greenfox.Controller;

import com.greenfox.Modell.User;
import com.greenfox.Repository.MessageRepository;
import com.greenfox.Repository.UserRepository;
import com.greenfox.Service.MessageOperator;
import com.greenfox.Service.UserOperator;
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

  private String missingUserName = "";
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private MessageOperator messageOperator;
  @Autowired
  UserOperator userOperator;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("messages", messageRepository.findAllByOrderByTimestampDesc());
    model.addAttribute("user", userRepository.findOne(1));
    model.addAttribute("error", missingUserName);
    return userRepository.count() == 0 ? "redirect:/enter" : "index";
  }

  @RequestMapping("/enter")
  public String enter(Model model) {
    model.addAttribute("error", missingUserName);
    return userRepository.count() > 0 ? "redirect:/" : "enter";
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
      userOperator.updateUser(name);
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
