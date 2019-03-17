package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.UserDAO;
import com.tuturugaNicolae.bestFurnitureDeals.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
    public UserDAOImpl() {
        super(User.class);
    }
}
