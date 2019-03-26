package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;

import java.util.List;

public interface BoughtFurnitureService {
    void addNewFurnitureToCurrentClientOrder(BoughtFurniture boughtFurniture, ClientOrder clientOrder);

    void updateBoughtFurniture(BoughtFurniture boughtFurniture);

    List<BoughtFurniture> getAllBoughtFurnitureForCurrentClientOrder(ClientOrder clientOrder);

    BoughtFurniture getBoughtFurnitureById(long id);

    void deleteBoughtFurniture(BoughtFurniture boughtFurniture);
}
