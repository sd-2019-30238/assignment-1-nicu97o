package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientOrderDAO extends JpaRepository<ClientOrder, Long> {
    Optional<ClientOrder> findClientOrderByUserUsernameAndAndFinishedFalse(String username);

    List<ClientOrder> findClientOrdersByUserUsername(String username);

    List<ClientOrder> findClientOrdersByApprovedFalseAndFinishedTrue();
}
