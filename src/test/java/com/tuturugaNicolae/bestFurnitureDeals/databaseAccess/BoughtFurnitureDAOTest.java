package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
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
}
