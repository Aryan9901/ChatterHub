package com.aryan.app.serviceImpl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.aryan.app.models.entity.User;
import com.aryan.app.repository.UserRepository;
import com.aryan.app.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User registerUser(User user) throws Exception {
        if (userRepository.existsById(user.getId())) {
            throw new Exception("user already exist!");
        }
        // Save new user
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        var user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User Not Exist with user id: " + userId);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        var user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User Not Exist with email: " + email);
    }

    @Override
    public User followUser(Integer followerId, Integer followeeId) throws Exception {
        // follower is the person who wants to follow
        User follower = findUserById(followerId);
        // followee is the follower wants to follow
        User followee = findUserById(followeeId);

        followee.getFollowers().add(follower.getId());
        follower.getFollowings().add(followee.getId());

        userRepository.save(followee);
        userRepository.save(follower);

        return follower;
    }

    @Override
    public User updateUser(User user) throws Exception {
        return userRepository.findById(user.getId()).map(existingUser -> {
            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + user.getId()));
    }

    @Override
    public List<User> searchUsers(String query) throws Exception {
        return userRepository.searchUser(query);
    }

}
