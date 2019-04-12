package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientOrderDAO extends JpaRepository<ClientOrder, Long> {
}
