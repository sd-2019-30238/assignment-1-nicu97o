package com.tuturugaNicolae.bestFurnitureDeals.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration.BusinessLogicConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoRightsToPerformThisOperationException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoUserFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityContext securityContext;

    @Before
    public void setUp() {
        securityContext.authenticate(PREINSERTED_USER_1.getUsername(), PREINSERTED_USER_1.getPassword());
    }

    @Test
    public void testGetUserById_shouldBeSuccessful() {
        User user = userService.getUserById(PREINSERTED_USER_1.getId());
        assertThat(user, is(PREINSERTED_USER_1));
    }

    @Test(expected = NoUserFoundException.class)
    public void testGetUserByIdWhenNoUserWithWantedIdExists_shouldThrowException() {
        userService.getUserById(-1);
    }

    @Test
    public void testGetUserByUsername_shouldBeSuccessful() {
        User user = userService.getUserByUsername(PREINSERTED_USER_1.getUsername());
        assertThat(user, is(PREINSERTED_USER_1));
    }

    @Test(expected = NoUserFoundException.class)
    public void testGetUserByUsernameWhenNoUserWithWantedUsernameExists_shouldThrowException() {
        userService.getUserByUsername("test");
    }

    @Test
    public void testGetAllUsers_shouldBeSuccessful() {
        List<User> users = userService.getAllUsers();
        assertThat(users.size(), is(NUMBER_OF_PREINSERTED_USERS));
    }

    @Test
    public void testUpdateUser_shouldBeSuccessful() {
        User user = new User(PREINSERTED_USER_1.getId(), PREINSERTED_USER_1.getUsername(), "test1231", "test@yahoo.com", PREINSERTED_USER_1.getUserType());
        userService.updateUser(user, PREINSERTED_USER_1.getUsername());
        User updatedUser = userService.getUserByUsername(PREINSERTED_USER_1.getUsername());
        assertThat(updatedUser, is(user));
    }

    @Test(expected = NoRightsToPerformThisOperationException.class)
    public void testUpdateUserWhenLoggedUsernameIsDifferentFromUserToUpdate_shouldThrowException() {
        User user = new User(null, PREINSERTED_USER_1.getUsername(), "test1231", "test@yahoo.com", PREINSERTED_USER_1.getUserType());
        userService.updateUser(user, PREINSERTED_USER_2.getUsername());
    }

    @Test
    public void testDeleteUser_shouldBeSuccessful() {
        userService.deleteUser(PREINSERTED_USER_1.getUsername());
        List<User> usersLeftAfterDelete = userService.getAllUsers();
        assertThat(usersLeftAfterDelete.size(), is(NUMBER_OF_PREINSERTED_USERS - 1));
        assertTrue(!usersLeftAfterDelete.contains(PREINSERTED_USER_1));
    }

    @Test
    public void testAddUser_shouldBeSuccessful() {
        User user = new User(null, "test", "test123", "test@yahoo.com", UserType.CLIENT);
        userService.addUser(user, "test123");
        List<User> users = userService.getAllUsers();
        assertThat(users.size(), is(NUMBER_OF_PREINSERTED_USERS + 1));
    }
}
