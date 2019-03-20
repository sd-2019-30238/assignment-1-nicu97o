package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.model.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByUsername(String username);
}
