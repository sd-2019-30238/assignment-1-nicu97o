package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.DealDAO;
import com.tuturugaNicolae.bestFurnitureDeals.model.Deal;
import org.springframework.stereotype.Repository;

@Repository
public class DealDAOImpl extends GenericDAOImpl<Deal> implements DealDAO {
    public DealDAOImpl() {
        super(Deal.class);
    }
}
