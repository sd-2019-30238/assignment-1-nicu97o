package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.ClientOrderDAO;
import com.tuturugaNicolae.bestFurnitureDeals.model.ClientOrder;
import org.springframework.stereotype.Repository;

@Repository
public class ClientOrderDAOImpl extends GenericDAOImpl<ClientOrder> implements ClientOrderDAO {
    public ClientOrderDAOImpl() {
        super(ClientOrder.class);
    }
}
