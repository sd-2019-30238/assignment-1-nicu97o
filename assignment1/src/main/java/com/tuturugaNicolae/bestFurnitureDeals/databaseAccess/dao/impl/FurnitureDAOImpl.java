package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.FurnitureDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import org.springframework.stereotype.Repository;

@Repository
public class FurnitureDAOImpl extends GenericDAOImpl<Furniture> implements FurnitureDAO {
    public FurnitureDAOImpl() {
        super(Furniture.class);
    }
}
