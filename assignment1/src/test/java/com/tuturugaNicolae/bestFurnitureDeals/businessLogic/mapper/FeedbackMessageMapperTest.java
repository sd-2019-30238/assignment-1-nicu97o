package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.mapper;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.ClientOrderMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.FeedbackMessageMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.OrderHistoryMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.UserMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FeedbackMessageDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.OrderHistoryDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import org.junit.Before;
import org.junit.Test;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FeedbackMessageMapperTest {
    private Mapper<FeedbackMessage, FeedbackMessageDTO> feedbackMapper;
    private Mapper<OrderHistory, OrderHistoryDTO> orderHistoryMapper;
    private Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper;
    private Mapper<User, UserDTO> userMapper;

    @Before
    public void setUp() {
        userMapper = new UserMapper();
        clientOrderMapper = new ClientOrderMapper(userMapper);
        orderHistoryMapper = new OrderHistoryMapper(clientOrderMapper);
        feedbackMapper = new FeedbackMessageMapper(orderHistoryMapper);
    }

    @Test
    public void testConvertToEntity_shouldBeSuccessful() {
        FeedbackMessage feedbackMessage = feedbackMapper.convertToEntity(PREINSERTED_FEEDBACK_MESSAGEDTO_1);
        assertThat(feedbackMessage, is(PREINSERTED_FEEDBACK_MESSAGE_1));
    }

    @Test
    public void testConvertToDTO_shouldBeSuccessful() {
        FeedbackMessageDTO feedbackMessageDTO = feedbackMapper.convertToDTO(PREINSERTED_FEEDBACK_MESSAGE_1);
        assertThat(feedbackMessageDTO, is(PREINSERTED_FEEDBACK_MESSAGEDTO_1));
    }
}
