package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.model.User;
import com.tuturugaNicolae.bestFurnitureDeals.model.UserType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDAOTest extends GenericDAOTest<User> {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(userDAO, NUMBER_OF_PREINSERTED_USERS);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(userDAO, PREINSERTED_USER_1.getId(), PREINSERTED_USER_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        User user = new User("nicu", "123", "nicu.tuturuga@yahoo.com", UserType.STAFF);
        testInsert(userDAO, user, NUMBER_OF_PREINSERTED_USERS + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        User user = new User(1l, "nicu", "123", "nicu.tuturuga@yahoo.com", UserType.STAFF);
        testUpdate(userDAO, user, user.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        testDelete(userDAO, PREINSERTED_USER_1.getId(), NUMBER_OF_PREINSERTED_USERS - 1);
    }

    @Test
    public void testFindUserByUsername_shouldBeSuccessful() {
        User user = userDAO.findByUsername(PREINSERTED_USER_1.getUsername()).get();
        assertThat(user, is(PREINSERTED_USER_1));
    }
}
