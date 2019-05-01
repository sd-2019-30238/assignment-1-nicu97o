package com.bestfurnituredeals.assignment3.model.read.mapper.impl;

import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.Product;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.model.read.dto.DealDTO;
import com.bestfurnituredeals.assignment3.model.read.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {
    private Mapper<Deal, DealDTO> mapper;

    @Autowired
    public ProductMapper(Mapper<Deal, DealDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setSelectedQuantity(productDTO.getSelectedQuantity());
        product.setPrice(productDTO.getPrice());
        if (productDTO.getDeal() != null) {
            product.setDeal(mapper.convertToEntity(productDTO.getDeal()));
        }
        return product;
    }

    @Override
    public ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setSelectedQuantity(product.getSelectedQuantity());
        productDTO.setPrice(product.getPrice());
        if (product.getDeal() != null) {
            productDTO.setDeal(mapper.convertToDTO(product.getDeal()));
        }
        return productDTO;
    }
}
