package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureDAO extends JpaRepository<Furniture, Long> {
}
