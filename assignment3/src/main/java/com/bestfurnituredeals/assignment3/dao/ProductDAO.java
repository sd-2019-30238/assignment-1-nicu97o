package com.bestfurnituredeals.assignment3.dao;

import com.bestfurnituredeals.assignment3.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Long> {
    List<Product> findProductsByClientOrderId(long clientOrderId);
}
