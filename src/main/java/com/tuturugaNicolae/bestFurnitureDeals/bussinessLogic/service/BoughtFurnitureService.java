package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;

import java.util.List;

public interface BoughtFurnitureService {
    void addNewFurnitureToCurrentClientOrder(BoughtFurniture boughtFurniture, ClientOrder clientOrder);

    void updateFurniture(BoughtFurniture boughtFurniture);

    List<BoughtFurniture> getAllBoughtFurnitureForCurrentClientOrder(ClientOrder clientOrder);

    BoughtFurniture getBoughtFurnitureById(long id);

    void deleteBoughtFurniture(BoughtFurniture boughtFurniture);
}
