package com.demo.imager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.demo.imager.repositories.UserRepository;
import com.demo.imager.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
