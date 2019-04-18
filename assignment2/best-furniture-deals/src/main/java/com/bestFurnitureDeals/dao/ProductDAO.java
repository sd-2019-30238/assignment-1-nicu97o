package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Long> {
    List<Product> findProductsByClientOrderId(long clientOrderId);
}
