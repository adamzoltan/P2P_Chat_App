package com.greenfox.Contoller;

import com.greenfox.Modell.User;
import com.greenfox.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Adam on 2017. 05. 17..
 */

@Controller
public class MainController {

  String missingUserName = "";

  @Autowired
  UserRepository userRepository;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("users", userRepository.findAll());
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
  public String addUser(Model model, @RequestParam(name = "name", required = false) String name) {
    if (name.isEmpty()) {
      missingUserName = "The username field is empty";
      return "redirect:/enter";
    } else {
      userRepository.save(new User(name));
      missingUserName = "";
      return "redirect:/";
    }
  }

}
