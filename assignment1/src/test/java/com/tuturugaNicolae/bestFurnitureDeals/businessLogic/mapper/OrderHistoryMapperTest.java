package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.mapper;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.ClientOrderMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.OrderHistoryMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.UserMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.OrderHistoryDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import org.junit.Before;
import org.junit.Test;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_ORDER_HISTORYDTO_1;
import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_ORDER_HISTORY_1;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OrderHistoryMapperTest {
    private Mapper<OrderHistory, OrderHistoryDTO> orderHistoryMapper;
    private Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper;
    private Mapper<User, UserDTO> userMapper;

    @Before
    public void setUp() {
        userMapper = new UserMapper();
        clientOrderMapper = new ClientOrderMapper(userMapper);
        orderHistoryMapper = new OrderHistoryMapper(clientOrderMapper);
    }

    @Test
    public void testConvertToEntity_shouldBeSuccessful() {
        OrderHistory orderHistory = orderHistoryMapper.convertToEntity(PREINSERTED_ORDER_HISTORYDTO_1);
        assertThat(orderHistory, is(PREINSERTED_ORDER_HISTORY_1));
    }

    @Test
    public void testConvertToDTO_shouldBeSuccessful() {
        OrderHistoryDTO orderHistoryDTO = orderHistoryMapper.convertToDTO(PREINSERTED_ORDER_HISTORY_1);
        assertThat(orderHistoryDTO, is(PREINSERTED_ORDER_HISTORYDTO_1));
    }
}
