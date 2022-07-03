package com.mechanitis.demo.stockui;

import com.mechanitis.demo.Backend.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);




}
