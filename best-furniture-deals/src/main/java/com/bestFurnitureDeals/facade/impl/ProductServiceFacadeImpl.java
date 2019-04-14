package com.bestFurnitureDeals.facade.impl;

import com.bestFurnitureDeals.dto.mapper.Mapper;
import com.bestFurnitureDeals.dto.model.ProductDTO;
import com.bestFurnitureDeals.facade.ProductServiceFacade;
import com.bestFurnitureDeals.model.Product;
import com.bestFurnitureDeals.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductServiceFacadeImpl implements ProductServiceFacade {
    private Mapper<Product, ProductDTO> mapper;
    private ProductService productService;

    @Autowired
    public ProductServiceFacadeImpl(Mapper<Product, ProductDTO> mapper, ProductService productService) {
        this.mapper = mapper;
        this.productService = productService;
    }

    @Override
    public ProductDTO getProductById(long id) {
        return mapper.convertToDTO(productService.getProductById(id));
    }

    @Override
    public void addProductToOrder(long dealId, String username, int quantity) {
        productService.addProductToOrder(dealId, username, quantity);
    }

    @Override
    public void deleteProduct(long id) {
        productService.deleteProduct(id);
    }

    @Override
    public List<ProductDTO> getProductsForAClientOrder(long id) {
        return productService.getProductsForAClientOrder(id).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsForAClientsCurrentOrder(String username) {
        return productService.getProductsForAClientsCurrentOrder(username).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }
}
