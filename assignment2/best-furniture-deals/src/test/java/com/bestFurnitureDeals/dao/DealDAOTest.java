package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.DealType;
import com.bestFurnitureDeals.model.Furniture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DealDAOTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DealDAO dealDAO;
    private Furniture furniture;
    private Deal deal;
    private Deal deal2;
    private Deal deal3;

    @Before
    public void setUp() {
        furniture = new Furniture(null, "name", "description", new ArrayList<>());
        entityManager.persist(furniture);
        deal = new Deal(null, "deal name 1", DealType.NORMAL, BigDecimal.ONE, false, 1, furniture, new ArrayList<>());
        entityManager.persist(deal);
        deal2 = new Deal(null, "deal name 2", DealType.REDUCED, BigDecimal.TEN, true, 1, furniture, new ArrayList<>());
        entityManager.persist(deal2);
        deal3 = new Deal(null, "deal name 3", DealType.NORMAL, BigDecimal.ZERO, true, 1, furniture, new ArrayList<>());
        entityManager.persist(deal3);
        entityManager.flush();
    }

    @Test
    public void testFindDealsByAvailableTrue_shouldBeSuccessful() {
        List<Deal> deals = dealDAO.findDealsByAvailableTrue();
        assertTrue(deals.contains(deal3));
        assertTrue(deals.contains(deal2));
        assertTrue(!deals.contains(deal));
    }

    @Test
    public void testFindDealsByPriceIsBetweenAndAvailableTrue_shouldBeSuccessful() {
        List<Deal> deals = dealDAO.findDealsByPriceIsBetweenAndAvailableTrue(BigDecimal.ONE, BigDecimal.TEN);
        assertTrue(!deals.contains(deal3));
        assertTrue(deals.contains(deal2));
        assertTrue(!deals.contains(deal));
    }

    @Test
    public void testFindDealsByAvailableTrueAndNameContains_shouldBeSuccessful() {
        List<Deal> deals = dealDAO.findDealsByAvailableTrueAndNameContains("deal name 3");
        assertTrue(deals.contains(deal3));
        assertTrue(!deals.contains(deal2));
        assertTrue(!deals.contains(deal));
    }

    @Test
    public void testFindDealsByAvailableTrueAndDealType_shouldBeSuccessful() {
        List<Deal> deals = dealDAO.findDealsByAvailableTrueAndDealType(DealType.REDUCED);
        assertTrue(!deals.contains(deal3));
        assertTrue(deals.contains(deal2));
        assertTrue(!deals.contains(deal));
    }
}
