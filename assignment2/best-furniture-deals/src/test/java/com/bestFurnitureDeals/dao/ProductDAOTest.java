package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductDAOTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductDAO productDAO;

    @Test
    public void testFindProductByClientOrderId() {
        Furniture furniture = new Furniture(null, "Scaun", "Scaun desc", new ArrayList<>());
        entityManager.persist(furniture);
        Deal deal = new Deal(null, "Deal scaun", DealType.NORMAL, BigDecimal.ONE, true, 1, furniture, new ArrayList<>());
        entityManager.persist(deal);
        ClientOrder clientOrder = new ClientOrder(null, false, PaymentMethod.CASH, BigDecimal.ZERO, false, null, new ArrayList<>(), null);
        entityManager.persist(clientOrder);
        Product product = new Product(null, 1, BigDecimal.ONE, deal, clientOrder);
        entityManager.persist(product);
        entityManager.flush();
        Product foundProduct = productDAO.findProductsByClientOrderId(1L).get(0);
        assertThat(foundProduct, is(product));
    }
}
