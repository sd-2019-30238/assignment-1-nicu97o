package com.tuturugaNicolae.bestFurnitureDeals.service;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;

public interface DealComputerService {
    BoughtFurniture computeFurnitureOrderDetails(ClientOrder clientOrder, Deal deal, int quantity);
}
