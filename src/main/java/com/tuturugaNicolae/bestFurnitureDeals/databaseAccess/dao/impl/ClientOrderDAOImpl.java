package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.ClientOrderDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import org.springframework.stereotype.Repository;

@Repository
public class ClientOrderDAOImpl extends GenericDAOImpl<ClientOrder> implements ClientOrderDAO {
    public ClientOrderDAOImpl() {
        super(ClientOrder.class);
    }
}
