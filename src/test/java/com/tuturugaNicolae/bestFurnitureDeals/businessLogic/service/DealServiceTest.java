package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration.BusinessLogicConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception.NoDealFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.DealType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class DealServiceTest {
    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private DealService dealService;

    @Before
    public void setUp() {
        securityContext.authenticate(PREINSERTED_USER_2.getUsername(), PREINSERTED_USER_2.getPassword());
    }

    @Test
    public void testGetDealsByType_shouldBeSuccessful() {
        List<Deal> deals = dealService.getDealsByType(DealType.NORMAL);
        assertTrue(deals.contains(PREINSERTED_DEAL_2));
    }

    @Test(expected = NoDealFoundException.class)
    public void testGetDealsByTypeWhenNoDealsFound_shouldThrowException() {
        dealService.getDealsByType(DealType.PAY_ONE_AND_TAKE_SECOND_FREE);
    }

    @Test
    public void testGetDealsByPrice_shouldBeSuccessful() {
        List<Deal> deals = dealService.getDealsByPrice(PREINSERTED_DEAL_1.getPrice(), PREINSERTED_DEAL_1.getPrice());
        assertTrue(deals.contains(PREINSERTED_DEAL_1));
        assertTrue(!deals.contains(PREINSERTED_DEAL_2));
    }

    @Test(expected = NoDealFoundException.class)
    public void testGetDealsByPriceWhenNoDealsFound_shouldThrowException() {
        dealService.getDealsByPrice(BigDecimal.valueOf(100), BigDecimal.valueOf(200));
    }

    @Test
    public void testGetDealsByName_shouldBeSuccessful() {
        List<Deal> deals = dealService.getDealsByName(PREINSERTED_DEAL_2.getName());
        assertTrue(!deals.contains(PREINSERTED_DEAL_1));
        assertTrue(deals.contains(PREINSERTED_DEAL_2));
    }

    @Test(expected = NoDealFoundException.class)
    public void testGetDealsByNameWhenNoDealsFound_shouldThrowException() {
        dealService.getDealsByName("test");
    }

    @Test
    public void testGetAllDeals() {
        List<Deal> deals = dealService.getAllDeals();
        assertThat(deals.size(), is(NUMBER_OF_PREINSERTED_DEALS));
    }

    @Test
    public void testGetDealById_shouldBeSuccessful() {
        Deal deal = dealService.getDealById(PREINSERTED_DEAL_1.getId());
        assertThat(deal, is(PREINSERTED_DEAL_1));
    }

    @Test(expected = NoDealFoundException.class)
    public void testGetDealByIdWhenNoDealFound_shouldThrowException() {
        dealService.getDealById(NUMBER_OF_PREINSERTED_DEALS + 1);
    }

    @Test
    public void testAddDeal_shouldBeSuccessful() {
        dealService.addDeal(new Deal(null, "test", DealType.PAY_ONE_AND_TAKE_SECOND_FREE, PREINSERTED_FURNITURE_1, BigDecimal.ONE, true, 1));
        assertThat(dealService.getAllDeals().size(), is(NUMBER_OF_PREINSERTED_DEALS + 1));
    }

    @Test
    public void testUpdateDeal_shouldBeSuccessful() {
        Deal deal = new Deal(PREINSERTED_DEAL_1.getId(), "test", DealType.PAY_ONE_AND_TAKE_SECOND_FREE, PREINSERTED_DEAL_1.getFurniture(), BigDecimal.ZERO, false, 0);
        dealService.updateDeal(deal);
        assertThat(dealService.getDealById(deal.getId()), is(deal));
    }

    @Test
    public void testDeleteDeal_shouldBeSuccessful() {
        dealService.deleteDeal(PREINSERTED_DEAL_1);
        assertThat(dealService.getAllDeals().size(), is(NUMBER_OF_PREINSERTED_DEALS - 1));
    }

    @Test
    public void testUpdateDealQuantityAndAvailability_shouldBeSuccessful() {
        Deal deal = new Deal(PREINSERTED_DEAL_1.getId(), PREINSERTED_DEAL_1.getName(), PREINSERTED_DEAL_1.getDealType(), PREINSERTED_DEAL_1.getFurniture(), PREINSERTED_DEAL_1.getPrice(), PREINSERTED_DEAL_1.isAvailable(), 1000);
        dealService.updateDealQuantityAndAvailability(deal);
        assertThat(deal, is(dealService.getDealById(deal.getId())));
    }
}
