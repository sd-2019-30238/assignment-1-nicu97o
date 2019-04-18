package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.FeedbackMessageDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FeedbackMessageDAOImpl extends GenericDAOImpl<FeedbackMessage> implements FeedbackMessageDAO {
    public FeedbackMessageDAOImpl() {
        super(FeedbackMessage.class);
    }

    @Override
    public Optional<FeedbackMessage> getFeedbackMessageByClientOrderId(long id) {
        return sessionFactory.getCurrentSession().createQuery("from " + FeedbackMessage.class.getName() + " f where f.orderHistory.clientOrder.id =: id")
                .setParameter("id", id).uniqueResultOptional();
    }
}
