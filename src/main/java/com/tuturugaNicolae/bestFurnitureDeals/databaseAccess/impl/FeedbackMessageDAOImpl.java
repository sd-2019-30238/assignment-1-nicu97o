package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.FeedbackMessageDAO;
import com.tuturugaNicolae.bestFurnitureDeals.model.FeedbackMessage;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackMessageDAOImpl extends GenericDAOImpl<FeedbackMessage> implements FeedbackMessageDAO {
    public FeedbackMessageDAOImpl() {
        super(FeedbackMessage.class);
    }
}
