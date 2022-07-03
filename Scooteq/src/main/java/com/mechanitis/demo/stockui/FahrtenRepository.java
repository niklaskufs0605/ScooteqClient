package com.mechanitis.demo.stockui;

import com.mechanitis.demo.Backend.Fahrt;
import com.mechanitis.demo.Backend.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface FahrtenRepository extends MongoRepository<Fahrt, Integer> {





}
