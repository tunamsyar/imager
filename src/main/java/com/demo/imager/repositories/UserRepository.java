package com.demo.imager.repositories;

import com.demo.imager.models.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  User findByUsername(String username);

  User findByEmail(String email);

  List<User> findAll();
}
