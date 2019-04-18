package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BoughtFurnitureDAOTest extends GenericDAOTest<BoughtFurniture> {
    @Autowired
    private BoughtFurnitureDAO boughtFurnitureDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(boughtFurnitureDAO, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(boughtFurnitureDAO, PreinsertedDataContainer.PREINSERTED_BOUGHT_FURNITURE_1.getId(), PreinsertedDataContainer.PREINSERTED_BOUGHT_FURNITURE_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        BoughtFurniture boughtFurniture = new BoughtFurniture(0L, PreinsertedDataContainer.PREINSERTED_FURNITURE_1, 2, BigDecimal.ZERO, new ClientOrder());
        testInsert(boughtFurnitureDAO, boughtFurniture, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        BoughtFurniture boughtFurniture = new BoughtFurniture(PreinsertedDataContainer.PREINSERTED_BOUGHT_FURNITURE_1.getId(), PreinsertedDataContainer.PREINSERTED_FURNITURE_1, 2, BigDecimal.ZERO, new ClientOrder());
        testUpdate(boughtFurnitureDAO, boughtFurniture, PreinsertedDataContainer.PREINSERTED_FURNITURE_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        testDelete(boughtFurnitureDAO, PreinsertedDataContainer.PREINSERTED_BOUGHT_FURNITURE_1.getId(), PreinsertedDataContainer.NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE - 1);
    }

    @Test
    public void testFindCurrentProductsForCurrentClientOrder_shouldBeSuccessful(){
        List<BoughtFurniture> boughtFurniture = boughtFurnitureDAO.findCurrentProductsForCurrentClientOrder(PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1.getId());
        assertTrue(boughtFurniture.contains(PreinsertedDataContainer.PREINSERTED_BOUGHT_FURNITURE_1));
    }
}
