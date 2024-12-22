package com.aryan.app.controller;

import org.springframework.web.bind.annotation.RestController;
import com.aryan.app.models.User;
import com.aryan.app.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/create")
    public User createUser(@RequestBody User user) {
        if (user.getId() == 0 || user.getEmail() == "" || user.getFirstName() == ""
                || user.getLastName() == "") {
            return null;
        }

        Optional<User> isUserPresent = userRepository.findById(user.getId());
        System.out.println(isUserPresent);
        User createdUser = null;
        if (isUserPresent.isEmpty()) {
            createdUser = userRepository.save(user);
        }
        return createdUser;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }

    @GetMapping("/users/{userid}")
    public Optional<User> getUserById(@PathVariable("userid") int id) {
        System.out.println(id);
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @PutMapping("/users/update")
    public User updateUser(@RequestBody User user) {
        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    @DeleteMapping("/users/{userid}")
    public String deleteUser(@PathVariable int userid) {
        userRepository.deleteById(userid);
        return "user deleted successfully with id " + userid;
    }
}
