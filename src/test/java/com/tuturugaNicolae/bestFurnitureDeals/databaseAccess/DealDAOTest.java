package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.model.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.model.DealType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
        Deal deal = new Deal(0l, "Test", DealType.REDUCED, PREINSERTED_FURNITURE_1, BigDecimal.ONE, true, 1);
        testInsert(dealDAO, deal, NUMBER_OF_PREINSERTED_DEALS + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        Deal deal = new Deal(PREINSERTED_DEAL_1.getId(), "Test", DealType.REDUCED, PREINSERTED_FURNITURE_1, BigDecimal.ONE, true, 1);
        testUpdate(dealDAO, deal, PREINSERTED_DEAL_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        testDelete(dealDAO, PREINSERTED_DEAL_1.getId(), NUMBER_OF_PREINSERTED_DEALS - 1);
    }

    @Test
    public void testFindDealsByPrice_shouldBeSuccessful() {
        List<Deal> deals = dealDAO.findDealsByPrice(PREINSERTED_DEAL_2.getPrice(), PREINSERTED_DEAL_1.getPrice());
        assertTrue(deals.containsAll(Arrays.asList(PREINSERTED_DEAL_1, PREINSERTED_DEAL_2)));
    }

    @Test
    public void testFindDealsByName_shouldBeSuccessful() {
        List<Deal> deals = dealDAO.findDealsByName("Chair");
        assertTrue(deals.containsAll(Arrays.asList(PREINSERTED_DEAL_1, PREINSERTED_DEAL_2)));
    }
}
