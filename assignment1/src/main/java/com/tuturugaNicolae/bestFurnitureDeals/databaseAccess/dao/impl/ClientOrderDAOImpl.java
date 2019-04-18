package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.ClientOrderDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientOrderDAOImpl extends GenericDAOImpl<ClientOrder> implements ClientOrderDAO {
    public ClientOrderDAOImpl() {
        super(ClientOrder.class);
    }

    @Override
    public Optional<ClientOrder> findClientOrderByUser(String username) {
        return sessionFactory.getCurrentSession().createQuery("from " + ClientOrder.class.getName() + " o where o.client.username = :username and " +
                "o.finished = false").setParameter("username", username).uniqueResultOptional();
    }

    @Override
    public List<ClientOrder> findAllFinishedOrdersForAnUser(String username) {
        return sessionFactory.getCurrentSession().createQuery("from " + ClientOrder.class.getName() + " o where o.client.username = :username and " +
                "o.finished = true").setParameter("username", username).list();
    }

    @Override
    public List<ClientOrder> getAllFinishedOrders() {
        return sessionFactory.getCurrentSession().createQuery("from " + ClientOrder.class.getName() + " o where " +
                "o.finished = true").list();
    }
}
