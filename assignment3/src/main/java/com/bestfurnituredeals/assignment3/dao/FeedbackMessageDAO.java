package com.bestfurnituredeals.assignment3.dao;

import com.bestfurnituredeals.assignment3.model.database.FeedbackMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackMessageDAO extends JpaRepository<FeedbackMessage, Long> {
    Optional<FeedbackMessage> findFeedbackMessageByOrderHistoryId(long orderHistoryId);
}
