package com.hcl.dadimusicapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dadimusicapi.exception.InvalidDataException;
import com.hcl.dadimusicapi.exception.NotFoundException;
import com.hcl.dadimusicapi.model.User;
import com.hcl.dadimusicapi.repo.UserRepo;

@Service
public class UserService {

  private static final Logger log = LogManager.getLogger(UserService.class);

  @Autowired
  private UserRepo userRepository;

  public User findByUsername(String username) {
    log.debug("Finding user with username: " + username);
    return this.userRepository.findByUsername(username).get();
  }

  public User findById(Integer id) {
    log.debug("Finding user with id: " + id);
    return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
  }

  public User addUser(User user) {
    log.debug("Trying to add user: " + user);

    // validate username not taken
    if(usernameTaken(user.getUsername())) {
      throw new InvalidDataException("Username is taken!");
    }
    return userRepository.save(user);
  }

  public Boolean usernameTaken(String username) {
    log.debug("Checking if username is taken: " + username);
    return this.userRepository.existsByUsername(username);
  }

}