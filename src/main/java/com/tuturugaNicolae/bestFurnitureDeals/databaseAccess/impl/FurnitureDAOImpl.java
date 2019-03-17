package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.FurnitureDAO;
import com.tuturugaNicolae.bestFurnitureDeals.model.Furniture;
import org.springframework.stereotype.Repository;

@Repository
public class FurnitureDAOImpl extends GenericDAOImpl<Furniture> implements FurnitureDAO {
    public FurnitureDAOImpl() {
        super(Furniture.class);
    }
}
