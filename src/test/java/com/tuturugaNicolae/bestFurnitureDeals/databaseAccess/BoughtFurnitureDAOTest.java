package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.model.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.model.ClientOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class BoughtFurnitureDAOTest extends GenericDAOTest<BoughtFurniture> {
    @Autowired
    private BoughtFurnitureDAO boughtFurnitureDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(boughtFurnitureDAO, NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(boughtFurnitureDAO, PREINSERTED_BOUGHT_FURNITURE_1.getId(), PREINSERTED_BOUGHT_FURNITURE_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        BoughtFurniture boughtFurniture = new BoughtFurniture(0L, PREINSERTED_FURNITURE_1, 2, BigDecimal.ZERO, new ClientOrder());
        testInsert(boughtFurnitureDAO, boughtFurniture, NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        BoughtFurniture boughtFurniture = new BoughtFurniture(PREINSERTED_BOUGHT_FURNITURE_1.getId(), PREINSERTED_FURNITURE_1, 2, BigDecimal.ZERO, new ClientOrder());
        testUpdate(boughtFurnitureDAO, boughtFurniture, PREINSERTED_FURNITURE_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        BoughtFurniture boughtFurniture = boughtFurnitureDAO.selectById(PREINSERTED_BOUGHT_FURNITURE_1.getId());
        testDelete(boughtFurnitureDAO, boughtFurniture, NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE - 1);
    }
}
