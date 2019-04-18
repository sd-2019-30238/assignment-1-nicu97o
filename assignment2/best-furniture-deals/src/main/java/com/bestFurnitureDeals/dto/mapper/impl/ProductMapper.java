package com.bestFurnitureDeals.dto.mapper.impl;

import com.bestFurnitureDeals.dto.mapper.Mapper;
import com.bestFurnitureDeals.dto.model.DealDTO;
import com.bestFurnitureDeals.dto.model.ProductDTO;
import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.Product;
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
