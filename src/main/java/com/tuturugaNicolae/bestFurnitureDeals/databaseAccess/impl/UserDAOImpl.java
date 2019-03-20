package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.UserDAO;
import com.tuturugaNicolae.bestFurnitureDeals.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return sessionFactory.getCurrentSession().createQuery("from " + User.class.getName() + " u where u.username = :username").setParameter("username", username).uniqueResultOptional();
    }
}
