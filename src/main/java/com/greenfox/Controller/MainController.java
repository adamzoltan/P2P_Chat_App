package com.greenfox.Controller;

import com.greenfox.Modell.Message;
import com.greenfox.Modell.MessageToBroadcast;
import com.greenfox.Modell.User;
import com.greenfox.Repository.MessageRepository;
import com.greenfox.Repository.UserRepository;
import com.greenfox.Service.MessageOperator;
import com.greenfox.Service.RandomIdGenerator;
import com.greenfox.Service.UserOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Adam on 2017. 05. 17..
 */

@Controller
public class MainController {

  String missingUserName = "";
  @Autowired
  UserRepository userRepository;
  @Autowired
  MessageRepository messageRepository;
  @Autowired
  MessageOperator messageOperator;
  @Autowired
  UserOperator userOperator;
  @Autowired
  RandomIdGenerator randomIdGenerator;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("messages", messageRepository.findAllByOrderByTimestampAsc());
    model.addAttribute("user", userRepository.findOne(1));
    model.addAttribute("error", missingUserName);
    if(userRepository.count() == 0) {
      return "redirect:/enter";
    } else return "index";
  }

  @RequestMapping("/enter")
  public String enter(Model model) {
    model.addAttribute("error", missingUserName);
    if(userRepository.count() > 0) {
      return "redirect:/";
    } else return "enter";
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
    Message newMessage = new Message(userRepository.findOne(1).getName(),message);
    randomIdGenerator.generateId(newMessage);
    messageRepository.save(newMessage);
    MessageToBroadcast messageToBroadcast = new MessageToBroadcast();
    messageToBroadcast.getClient().setId(System.getenv("CHAT_APP_UNIQUE_ID"));
    messageToBroadcast.setMessage(newMessage);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForLocation(System.getenv("CHAT_APP_PEER_ADDRESS"), messageToBroadcast);

    return "redirect:/";
  }

}
