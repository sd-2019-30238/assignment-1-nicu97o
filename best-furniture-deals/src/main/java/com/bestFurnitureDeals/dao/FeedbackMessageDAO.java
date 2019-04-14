package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.FeedbackMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackMessageDAO extends JpaRepository<FeedbackMessage, Long> {
    Optional<FeedbackMessage> findFeedbackMessageByOrderHistoryId(long orderHistoryId);
}
