package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.mapper;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.ClientOrderMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.UserMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import org.junit.Before;
import org.junit.Test;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClientOrderMapperTest {
    private Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper;
    private Mapper<User, UserDTO> userMapper;

    @Before
    public void setUp() {
        userMapper = new UserMapper();
        clientOrderMapper = new ClientOrderMapper(userMapper);
    }

    @Test
    public void testConvertToEntity_shouldBeSuccessful() {
        ClientOrder clientOrder = clientOrderMapper.convertToEntity(PREINSERTED_CLIENT_ORDERDTO_1);
        assertThat(clientOrder, is(PREINSERTED_CLIENT_ORDER_1));
    }

    @Test
    public void testConvertToDTO_shouldBeSuccessful() {
        ClientOrderDTO clientOrderDTO = clientOrderMapper.convertToDTO(PREINSERTED_CLIENT_ORDER_1);
        assertThat(clientOrderDTO, is(PREINSERTED_CLIENT_ORDERDTO_1));
    }
}
