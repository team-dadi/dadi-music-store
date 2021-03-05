package com.hcl.dadimusicapi.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dadimusicapi.model.User;
import com.hcl.dadimusicapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  private static final Logger log = LogManager.getLogger(UserController.class);

  @GetMapping("/{id}")
  public User get(@PathVariable Integer id){
    return userService.findById(id);
  }

  @GetMapping("/username/{username}")
  public User getByUsername(@PathVariable String username){
    return userService.findByUsername(username);
  }

  @PostMapping
  public User add(@RequestBody User user){
    return userService.addUser(user);
  }

  @GetMapping("/taken/{username}")
  public Boolean usernameTaken(@PathVariable String username){
    return userService.usernameTaken(username);
  }
}