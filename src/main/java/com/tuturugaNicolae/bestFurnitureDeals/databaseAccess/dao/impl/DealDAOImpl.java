package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.DealDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.DealType;
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

    @Override
    public List<Deal> findDealsByType(DealType dealType) {
        return sessionFactory.getCurrentSession().createQuery("from " + Deal.class.getName() + " d where d.dealType = :dealType").setParameter("dealType", dealType).list();
    }
}
