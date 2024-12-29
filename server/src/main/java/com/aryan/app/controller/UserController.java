package com.aryan.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.aryan.app.models.entity.User;
import com.aryan.app.repository.UserRepository;
import com.aryan.app.service.UserService;

import lombok.AllArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@AllArgsConstructor
public class UserController {

    private UserRepository userRepository;
    UserService userService;

    @PostMapping("/users/create")
    public User createUser(@RequestBody User user) throws Exception {
        if (user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getFirstName() == null || user.getFirstName().isEmpty() ||
                user.getLastName() == null || user.getLastName().isEmpty() || user.getGender() == null
                || user.getGender().isEmpty()) {
            throw new Exception("all fields are required!");
        }

        return userService.registerUser(user);
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUsers(String query) throws Exception {
        return userService.searchUsers(query);
    }

    @GetMapping("/api/users/{userid}")
    public User getUserById(@PathVariable("userid") int id) throws Exception {
        return userService.findUserById(id);
    }

    @GetMapping("/api/users/find/{email}")
    public User getUserByEmail(@PathVariable String email) throws Exception {
        return userService.findUserByEmail(email);
    }

    @PutMapping("/api/users/{userId}")
    public User updateUser(@PathVariable("userId") int id, @RequestBody User user) throws Exception {
        user.setId(id);
        return userService.updateUser(user);
    }

    @PutMapping("/api/users/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        return userService.followUser(userId1, userId2);
    }

    @DeleteMapping("/api/users/{userid}")
    public String deleteUser(@PathVariable int userid) {

        if (!userRepository.existsById(userid)) {
            return "User not found with id " + userid;
        }

        userRepository.deleteById(userid);
        return "user deleted successfully with id " + userid;
    }
}
