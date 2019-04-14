package com.bestFurnitureDeals.facade;

import com.bestFurnitureDeals.dto.model.ProductDTO;

import java.util.List;

public interface ProductServiceFacade {
    ProductDTO getProductById(long id);

    void addProductToOrder(long dealId, String username, int quantity);

    void deleteProduct(long id);

    List<ProductDTO> getProductsForAClientOrder(long id);

    List<ProductDTO> getProductsForAClientsCurrentOrder(String username);
}
