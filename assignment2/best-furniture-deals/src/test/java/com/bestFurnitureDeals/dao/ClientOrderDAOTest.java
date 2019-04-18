package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.ClientOrder;
import com.bestFurnitureDeals.model.PaymentMethod;
import com.bestFurnitureDeals.model.User;
import com.bestFurnitureDeals.model.UserType;
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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientOrderDAOTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ClientOrderDAO clientOrderDAO;
    private ClientOrder currentClientOrder;
    private ClientOrder clientOrder;
    private User user;

    @Before
    public void setUp() {
        user = new User(null, "tuturuga", "pass", "tuturuganicu@gmail.com", UserType.CLIENT, new ArrayList<>());
        entityManager.persist(user);
        currentClientOrder = new ClientOrder(null, false, PaymentMethod.CASH, BigDecimal.ZERO, false, null, new ArrayList<>(), user);
        entityManager.persist(currentClientOrder);
        clientOrder = new ClientOrder(null, false, PaymentMethod.CASH, BigDecimal.ZERO, true, null, new ArrayList<>(), user);
        entityManager.persist(clientOrder);
        entityManager.flush();
    }

    @Test
    public void testFindClientOrderByUserUsernameAndAndFinishedFalse_shouldBeSuccessful() {
        ClientOrder foundClientOrder = clientOrderDAO.findClientOrderByUserUsernameAndAndFinishedFalse(user.getUsername()).get();
        assertThat(foundClientOrder, is(currentClientOrder));
    }

    @Test
    public void testFindClientOrdersByUserUsername_shouldBeSuccessful() {
        List<ClientOrder> clientOrders = clientOrderDAO.findClientOrdersByUserUsername(user.getUsername());
        assertTrue(clientOrders.size() == 2);
        assertTrue(clientOrders.contains(clientOrder));
        assertTrue(clientOrders.contains(currentClientOrder));
    }

    @Test
    public void testFindClientOrdersByApprovedFalseAndFinishedTrue_shouldBeSuccessful() {
        List<ClientOrder> clientOrders = clientOrderDAO.findClientOrdersByApprovedFalseAndFinishedTrue();
        assertTrue(clientOrders.size() == 1);
        assertTrue(clientOrders.contains(clientOrder));
        assertTrue(!clientOrders.contains(currentClientOrder));
    }
}
