package com.demorcapp.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demorcapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
