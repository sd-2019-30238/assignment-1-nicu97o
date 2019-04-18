package com.bestFurnitureDeals.dao;

import com.bestFurnitureDeals.model.User;
import com.bestFurnitureDeals.model.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDAOTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserDAO userDAO;
    private static final User USER = new User(null, "tuturuga", "pass", "tuturuganicu@gmail.com", UserType.CLIENT, new ArrayList<>());

    @Before
    public void setUp() {
        entityManager.persist(USER);
        entityManager.flush();
    }

    @Test
    public void testFindUserByUsername_shouldBeSuccessful() {
        User foundUser = userDAO.findUserByUsername(USER.getUsername()).get();
        assertThat(foundUser, is(USER));
        USER.setId(null);
    }

    @Test
    public void testFindUserByMail_shouldBeSuccessful() {
        User foundUser = userDAO.findUserByMail(USER.getMail()).get();
        assertThat(foundUser, is(USER));
        USER.setId(null);
    }
}
