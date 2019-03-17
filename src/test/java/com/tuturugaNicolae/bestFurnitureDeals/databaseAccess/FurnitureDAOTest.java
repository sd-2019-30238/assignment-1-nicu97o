package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.model.Furniture;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class FurnitureDAOTest extends GenericDAOTest<Furniture> {
    @Autowired
    private FurnitureDAO furnitureDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(furnitureDAO, NUMBER_OF_PREINSERTED_FURNITURE);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(furnitureDAO, PREINSERTED_FURNITURE_1.getId(), PREINSERTED_FURNITURE_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        Furniture furniture = new Furniture(0l, "Table", "Big", 10, new ArrayList<>(), new ArrayList<>());
        testInsert(furnitureDAO, furniture, NUMBER_OF_PREINSERTED_FURNITURE + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        Furniture furniture = new Furniture(PREINSERTED_FURNITURE_1.getId(), "Table", "Big", 10, new ArrayList<>(), new ArrayList<>());
        testUpdate(furnitureDAO, furniture, PREINSERTED_FURNITURE_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        Furniture furniture = furnitureDAO.selectById(PREINSERTED_FURNITURE_1.getId());
        testDelete(furnitureDAO, furniture, NUMBER_OF_PREINSERTED_FURNITURE - 1);
    }
}
