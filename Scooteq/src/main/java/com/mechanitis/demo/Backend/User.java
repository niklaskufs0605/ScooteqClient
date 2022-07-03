package com.mechanitis.demo.Backend;



import javafx.geometry.Side;
import javafx.scene.control.MenuButton;
import lombok.Getter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "Users")
public class User {

    private String password;
    @Indexed(unique = true)
    private String username;
    private String firstName;
    private String lastName;


    public User(String firstName, String lastName, String username, String password) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

    }




}
