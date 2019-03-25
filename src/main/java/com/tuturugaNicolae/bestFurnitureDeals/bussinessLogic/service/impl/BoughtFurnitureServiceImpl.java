package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.BoughtFurnitureService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.BoughtFurnitureDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoBoughtFurnitureFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoughtFurnitureServiceImpl implements BoughtFurnitureService {
    private BoughtFurnitureDAO boughtFurnitureDAO;

    @Autowired
    public BoughtFurnitureServiceImpl(BoughtFurnitureDAO boughtFurnitureDAO) {
        this.boughtFurnitureDAO = boughtFurnitureDAO;
    }

    @Override
    public void addNewFurnitureToCurrentClientOrder(BoughtFurniture boughtFurniture, ClientOrder clientOrder) {
        boughtFurniture.setClientOrder(clientOrder);
        boughtFurnitureDAO.insert(boughtFurniture);
    }

    @Override
    public void updateFurniture(BoughtFurniture boughtFurniture) {
        BoughtFurniture oldBoughtFurniture = getBoughtFurnitureById(boughtFurniture.getId());
        oldBoughtFurniture.setPrice(boughtFurniture.getPrice());
        oldBoughtFurniture.setBoughtQuantity(boughtFurniture.getBoughtQuantity());
    }

    @Override
    public List<BoughtFurniture> getAllBoughtFurnitureForCurrentClientOrder(ClientOrder clientOrder) {
        List<BoughtFurniture> boughtFurniture = boughtFurnitureDAO.findCurrentProductsForCurrentClientOrder(clientOrder.getId());
        if (boughtFurniture.isEmpty()) {
            throw new NoBoughtFurnitureFoundException();
        }
        return boughtFurniture;
    }

    @Override
    public BoughtFurniture getBoughtFurnitureById(long id) {
        BoughtFurniture boughtFurniture = boughtFurnitureDAO.selectById(id);
        if (boughtFurniture == null) {
            throw new NoBoughtFurnitureFoundException();
        }
        return boughtFurniture;
    }

    @Override
    public void deleteBoughtFurniture(BoughtFurniture boughtFurniture) {
        boughtFurnitureDAO.delete(getBoughtFurnitureById(boughtFurniture.getId()));
    }
}
