package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;

import java.util.Optional;

public interface FeedbackMessageDAO extends GenericDAO<FeedbackMessage> {
    Optional<FeedbackMessage> getFeedbackMessageByClientOrderId(long id);
}
