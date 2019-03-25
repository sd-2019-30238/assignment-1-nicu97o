package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;

import java.util.List;

public interface CartService {
    void addProductToCart(DealDTO dealDTO, int quantity);

    void deleteProductFromCart(BoughtFurnitureDTO boughtFurnitureDTO);

    List<BoughtFurnitureDTO> getAllProductsFromCart();
}
