package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.mapper;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.UserMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import org.junit.Before;
import org.junit.Test;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_USERDTO_1;
import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_USER_1;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserMapperTest {
    private Mapper<User, UserDTO> mapper;

    @Before
    public void setUp() {
        mapper = new UserMapper();
    }

    @Test
    public void testConvertToEntity_shouldBeSuccessful() {
        User user = mapper.convertToEntity(PREINSERTED_USERDTO_1);
        assertThat(user, is(PREINSERTED_USER_1));
    }

    @Test
    public void testConvertToDTO_shouldBeSuccessful(){
        UserDTO userDTO = mapper.convertToDTO(PREINSERTED_USER_1);
        assertThat(userDTO, is(PREINSERTED_USERDTO_1));
    }
}
