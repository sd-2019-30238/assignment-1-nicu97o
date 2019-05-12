package com.bestfurnituredeals.assignment3.facade.query;

import com.bestfurnituredeals.assignment3.model.read.dto.ProductDTO;

import java.util.List;

public interface ProductQueryServiceFacade {
    ProductDTO getProductById(long id);

    List<ProductDTO> getProductsForAClientOrder(long id);

    List<ProductDTO> getProductsForAClientsCurrentOrder(String username);
}
