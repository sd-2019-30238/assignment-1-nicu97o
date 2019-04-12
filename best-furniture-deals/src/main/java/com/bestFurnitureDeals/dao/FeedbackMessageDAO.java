package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.FeedbackMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackMessageDAO extends JpaRepository<FeedbackMessage, Long> {
}
