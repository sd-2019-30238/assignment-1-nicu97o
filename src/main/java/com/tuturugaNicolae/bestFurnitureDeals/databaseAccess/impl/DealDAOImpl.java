package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.DealDAO;
import com.tuturugaNicolae.bestFurnitureDeals.model.Deal;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class DealDAOImpl extends GenericDAOImpl<Deal> implements DealDAO {
    public DealDAOImpl() {
        super(Deal.class);
    }

    @Override
    public List<Deal> findDealsByPrice(BigDecimal lowerMargin, BigDecimal upperMargin) {
        return sessionFactory.getCurrentSession().createQuery("from " + Deal.class.getName() + " d where d.price >= :lowerMargin and d.price <= :upperMargin")
                .setParameter("lowerMargin", lowerMargin).setParameter("upperMargin", upperMargin).list();
    }

    @Override
    public List<Deal> findDealsByName(String dealsName) {
        return sessionFactory.getCurrentSession().createQuery("from " + Deal.class.getName() + " d where d.name like :name").setParameter("name", "%" + dealsName + "%").list();
    }
}
