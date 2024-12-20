package com.aryan.app.controller;

import org.springframework.web.bind.annotation.RestController;
import com.aryan.app.models.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        User user1 = new User("Aryan", "Gupta", "911aaryan@gmail.com", "password");
        User user2 = new User("Zosh", "Code", "zosh.code@gmail.com", "codezosh");
        User user3 = new User("Ayush", "kumar", "ayush10@gmail.com", "ayush@123");
        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }

}
