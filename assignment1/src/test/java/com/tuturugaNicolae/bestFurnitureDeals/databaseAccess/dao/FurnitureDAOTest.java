package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class FurnitureDAOTest extends GenericDAOTest<Furniture> {
    @Autowired
    private FurnitureDAO furnitureDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(furnitureDAO, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_FURNITURE);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(furnitureDAO, PreinsertedDataContainer.PREINSERTED_FURNITURE_1.getId(), PreinsertedDataContainer.PREINSERTED_FURNITURE_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        Furniture furniture = new Furniture(0l, "Table", "Big", new ArrayList<>(), new ArrayList<>());
        testInsert(furnitureDAO, furniture, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_FURNITURE + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        Furniture furniture = new Furniture(PreinsertedDataContainer.PREINSERTED_FURNITURE_1.getId(), "Table", "Big", new ArrayList<>(), new ArrayList<>());
        testUpdate(furnitureDAO, furniture, PreinsertedDataContainer.PREINSERTED_FURNITURE_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        testDelete(furnitureDAO, PreinsertedDataContainer.PREINSERTED_FURNITURE_1.getId(), PreinsertedDataContainer.NUMBER_OF_PREINSERTED_FURNITURE - 1);
    }
}
