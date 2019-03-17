package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.model.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.model.DealType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class DealDAOTest extends GenericDAOTest<Deal> {
    @Autowired
    private DealDAO dealDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(dealDAO, NUMBER_OF_PREINSERTED_DEALS);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(dealDAO, PREINSERTED_DEAL_1.getId(), PREINSERTED_DEAL_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        Deal deal = new Deal(0l, "Test", DealType.REDUCED, PREINSERTED_FURNITURE_1, BigDecimal.ONE, true);
        testInsert(dealDAO, deal, NUMBER_OF_PREINSERTED_DEALS + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        Deal deal = new Deal(PREINSERTED_DEAL_1.getId(), "Test", DealType.REDUCED, PREINSERTED_FURNITURE_1, BigDecimal.ONE, true);
        testUpdate(dealDAO, deal, PREINSERTED_DEAL_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        Deal deal = dealDAO.selectById(PREINSERTED_DEAL_1.getId());
        testDelete(dealDAO, deal, NUMBER_OF_PREINSERTED_DEALS - 1);
    }
}
