package com.bestfurnituredeals.assignment3.dao;

import com.bestfurnituredeals.assignment3.model.database.UserObserver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserObserverDAO extends JpaRepository<UserObserver, Long> {
}
