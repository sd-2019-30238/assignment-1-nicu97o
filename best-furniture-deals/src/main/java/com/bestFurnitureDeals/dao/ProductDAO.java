package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Long> {
}
