package com.bestfurnituredeals.assignment3.facade.query.impl;

import com.bestfurnituredeals.assignment3.facade.query.ProductQueryServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.Product;
import com.bestfurnituredeals.assignment3.model.read.dto.ProductDTO;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.service.query.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductQueryServiceFacadeImpl implements ProductQueryServiceFacade {
    private ProductQueryService productQueryService;
    private Mapper<Product, ProductDTO> mapper;

    @Autowired
    public ProductQueryServiceFacadeImpl(ProductQueryService productQueryService, Mapper<Product, ProductDTO> mapper) {
        this.productQueryService = productQueryService;
        this.mapper = mapper;
    }

    @Override
    public ProductDTO getProductById(long id) {
        return mapper.convertToDTO(productQueryService.getProductById(id));
    }

    @Override
    public List<ProductDTO> getProductsForAClientOrder(long id) {
        return productQueryService.getProductsForAClientOrder(id).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsForAClientsCurrentOrder(String username) {
        return productQueryService.getProductsForAClientsCurrentOrder(username).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }
}
