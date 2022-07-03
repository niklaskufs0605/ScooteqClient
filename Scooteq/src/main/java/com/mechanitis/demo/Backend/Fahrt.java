package com.mechanitis.demo.Backend;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Getter
@Setter
@Document(collection = "Fahrtkosten")
public class Fahrt {

    private int minutes;
    private int seconds;
    private Optional<User> user;
    private double price;

    public Fahrt(int minutes, int seconds, Optional<User> user, double price) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.user = user;
        this.price = price;
    }
}
