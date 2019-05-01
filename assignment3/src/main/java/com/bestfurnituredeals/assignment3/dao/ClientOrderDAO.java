package com.bestfurnituredeals.assignment3.dao;

import com.bestfurnituredeals.assignment3.model.database.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientOrderDAO extends JpaRepository<ClientOrder, Long> {
    Optional<ClientOrder> findClientOrderByUserUsernameAndAndFinishedFalse(String username);

    List<ClientOrder> findClientOrdersByUserUsername(String username);

    List<ClientOrder> findClientOrdersByApprovedFalseAndFinishedTrue();
}
