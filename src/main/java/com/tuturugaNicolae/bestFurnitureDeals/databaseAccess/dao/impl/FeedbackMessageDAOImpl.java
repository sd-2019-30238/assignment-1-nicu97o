package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.FeedbackMessageDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackMessageDAOImpl extends GenericDAOImpl<FeedbackMessage> implements FeedbackMessageDAO {
    public FeedbackMessageDAOImpl() {
        super(FeedbackMessage.class);
    }
}
