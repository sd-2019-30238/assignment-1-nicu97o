package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.UserDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
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
