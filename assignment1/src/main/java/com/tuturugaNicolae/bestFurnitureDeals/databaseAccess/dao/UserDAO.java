package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByUsername(String username);
}
