package com.bestfurnituredeals.assignment3.dao;

import com.bestfurnituredeals.assignment3.model.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByMail(String mail);
}
