package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.BoughtFurnitureDAO;
import com.tuturugaNicolae.bestFurnitureDeals.model.BoughtFurniture;
import org.springframework.stereotype.Repository;

@Repository
public class BoughtFurnitureDAOImpl extends GenericDAOImpl<BoughtFurniture> implements BoughtFurnitureDAO {
    public BoughtFurnitureDAOImpl() {
        super(BoughtFurniture.class);
    }
}
