package com.aryan.app.service;

import java.util.List;

import com.aryan.app.models.entity.User;

public interface UserService {
    public User registerUser(User user) throws Exception;

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    public User followUser(Integer followerId, Integer followeeId) throws Exception;

    public User updateUser(User user) throws Exception;

    public List<User> searchUsers(String query) throws Exception;
}
