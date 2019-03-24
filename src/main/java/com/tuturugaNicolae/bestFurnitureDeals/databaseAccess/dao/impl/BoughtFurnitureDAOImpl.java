package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.BoughtFurnitureDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import org.springframework.stereotype.Repository;

@Repository
public class BoughtFurnitureDAOImpl extends GenericDAOImpl<BoughtFurniture> implements BoughtFurnitureDAO {
    public BoughtFurnitureDAOImpl() {
        super(BoughtFurniture.class);
    }
}
