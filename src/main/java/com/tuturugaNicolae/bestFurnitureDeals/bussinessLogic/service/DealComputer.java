package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;

public interface DealComputer {
    BoughtFurnitureDTO computeFurnitureOrderDetails(ClientOrderDTO clientOrderDTO, DealDTO dealDTO, int quantity);
}
