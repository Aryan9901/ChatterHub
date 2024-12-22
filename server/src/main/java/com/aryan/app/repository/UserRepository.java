package com.aryan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aryan.app.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
