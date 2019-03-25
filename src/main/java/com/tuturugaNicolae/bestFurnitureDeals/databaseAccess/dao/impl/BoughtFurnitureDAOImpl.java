package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.BoughtFurnitureDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoughtFurnitureDAOImpl extends GenericDAOImpl<BoughtFurniture> implements BoughtFurnitureDAO {
    public BoughtFurnitureDAOImpl() {
        super(BoughtFurniture.class);
    }

    @Override
    public List<BoughtFurniture> findCurrentProductsForCurrentClientOrder(long orderId) {
        return sessionFactory.getCurrentSession().createQuery("from " + BoughtFurniture.class.getName() + " b where b.clientOrder.id =: orderId")
                .setParameter("orderId", orderId).list();
    }
}
