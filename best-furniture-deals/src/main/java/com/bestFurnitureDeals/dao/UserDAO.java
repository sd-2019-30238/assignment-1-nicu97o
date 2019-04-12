package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByMail(String mail);
}
