package com.bestfurnituredeals.assignment3.dao;

import com.bestfurnituredeals.assignment3.model.database.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureDAO extends JpaRepository<Furniture, Long> {
}
