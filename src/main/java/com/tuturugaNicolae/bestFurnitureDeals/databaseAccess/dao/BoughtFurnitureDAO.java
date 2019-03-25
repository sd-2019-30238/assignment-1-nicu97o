package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;

import java.util.List;

public interface BoughtFurnitureDAO extends GenericDAO<BoughtFurniture> {
    List<BoughtFurniture> findCurrentProductsForCurrentClientOrder(long orderId);
}
