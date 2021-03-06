package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.DealType;
import lombok.ToString;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.NUMBER_OF_PREINSERTED_DEALS;
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
        testSelectById(dealDAO, PreinsertedDataContainer.PREINSERTED_DEAL_1.getId(), PreinsertedDataContainer.PREINSERTED_DEAL_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        Deal deal = new Deal(0l, "Test", DealType.EACH_PRODUCT_REDUCTION_5_PERCENT, PreinsertedDataContainer.PREINSERTED_FURNITURE_1, BigDecimal.ONE, true, 1);
        testInsert(dealDAO, deal, NUMBER_OF_PREINSERTED_DEALS + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        Deal deal = new Deal(PreinsertedDataContainer.PREINSERTED_DEAL_1.getId(), "Test", DealType.EACH_PRODUCT_REDUCTION_5_PERCENT, PreinsertedDataContainer.PREINSERTED_FURNITURE_1, BigDecimal.ONE, true, 1);
        testUpdate(dealDAO, deal, PreinsertedDataContainer.PREINSERTED_DEAL_1.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        testDelete(dealDAO, PreinsertedDataContainer.PREINSERTED_DEAL_1.getId(), NUMBER_OF_PREINSERTED_DEALS - 1);
    }

    @Test
    public void testFindDealsByPrice_shouldBeSuccessful() {
        List<Deal> deals = dealDAO.findDealsByPrice(PreinsertedDataContainer.PREINSERTED_DEAL_2.getPrice(), PreinsertedDataContainer.PREINSERTED_DEAL_1.getPrice());
        assertTrue(deals.containsAll(Arrays.asList(PreinsertedDataContainer.PREINSERTED_DEAL_1, PreinsertedDataContainer.PREINSERTED_DEAL_2)));
    }

    @Test
    public void testFindDealsByName_shouldBeSuccessful() {
        List<Deal> deals = dealDAO.findDealsByName("Chair");
        assertTrue(deals.containsAll(Arrays.asList(PreinsertedDataContainer.PREINSERTED_DEAL_1, PreinsertedDataContainer.PREINSERTED_DEAL_2)));
    }

    @Test
    public void testFindDealsByType_shouldBeSuccessful(){
        List<Deal> deals = dealDAO.findDealsByType(DealType.EACH_PRODUCT_REDUCTION_5_PERCENT);
        assertThat(deals.size(), is(1));
    }
}
