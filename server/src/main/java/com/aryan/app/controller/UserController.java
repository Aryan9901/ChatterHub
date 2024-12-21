package com.aryan.app.controller;

import org.springframework.web.bind.annotation.RestController;
import com.aryan.app.models.User;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        User user1 = new User(1, "Aryan", "Gupta", "911aaryan@gmail.com", "password");
        User user2 = new User(2, "Zosh", "Code", "zosh.code@gmail.com", "codezosh");
        User user3 = new User(3, "Ayush", "kumar", "ayush10@gmail.com", "ayush@123");
        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }

    @GetMapping("/users/{userid}")
    public User getUserById(@PathVariable("userid") int id) {
        System.out.println(id);
        User user = new User(1, "Aryan", "Gupta", "911aaryan@gmail.com", "password");
        user.setId(id);
        return user;
    }

    @PostMapping("/users/create")
    public User createUser(@RequestBody User user) {
        System.out.println(user);
        return user;
    }

    @PutMapping("/users/update")
    public User updateUser(@RequestBody User user) {
        User updatedUser = new User();
        updatedUser.setEmail(user.getEmail());
        updatedUser.setId(user.getId());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPassword(user.getPassword());

        return updatedUser;
    }

    @DeleteMapping("/users/{userid}")
    public String deleteUser(@PathVariable int userid) {
        return "user deleted successfully with id " + userid;
    }
}
