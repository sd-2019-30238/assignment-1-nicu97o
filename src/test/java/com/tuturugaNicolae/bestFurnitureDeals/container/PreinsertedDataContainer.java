package com.tuturugaNicolae.bestFurnitureDeals.container;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.*;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class PreinsertedDataContainer {
    public static final User PREINSERTED_USER_1 = new User(1L, "tuturuga", "12345", "tuturuganicu@gmail.com", UserType.CLIENT);
    public static final User PREINSERTED_USER_2 = new User(2L, "nicu", "abcdefg", "nicu.tuturuga@yahoo.com", UserType.STAFF);
    public static final UserDTO PREINSERTED_USERDTO_1 = new UserDTO("tuturuga", "tuturuganicu@gmail.com", UserTypeDTO.CLIENT);
    public static final UserDTO PREINSERTED_USERDTO_2 = new UserDTO("nicu", "nicu.tuturuga@yahoo.com", UserTypeDTO.STAFF);
    public static final int NUMBER_OF_PREINSERTED_USERS = 2;

    public static final Furniture PREINSERTED_FURNITURE_1 = new Furniture(1L, "Chair", "Strong material");
    public static final FurnitureDTO PREINSERTED_FURNITUREDTO_1 = new FurnitureDTO(1L, "Chair", "Strong material");
    public static final int NUMBER_OF_PREINSERTED_FURNITURE = 1;

    public static final Deal PREINSERTED_DEAL_1 = new Deal(1L, "Chair", DealType.EACH_PRODUCT_REDUCTION_5_PERCENT, PREINSERTED_FURNITURE_1, BigDecimal.TEN, true, 1);
    public static final DealDTO PREINSERTED_DEALDTO_1 = new DealDTO(1L, "Chair", DealTypeDTO.EACH_PRODUCT_REDUCTION_5_PERCENT, PREINSERTED_FURNITUREDTO_1, BigDecimal.TEN, true, 1);
    public static final Deal PREINSERTED_DEAL_2 = new Deal(2L, "Chair 2", DealType.EACH_PRODUCT_REDUCTION_5_PERCENT, PREINSERTED_FURNITURE_1, BigDecimal.ONE, true, 1);
    public static final DealDTO PREINSERTED_DEALDTO_2 = new DealDTO(2L, "Chair 2", DealTypeDTO.EACH_PRODUCT_REDUCTION_5_PERCENT, PREINSERTED_FURNITUREDTO_1, BigDecimal.ONE, true, 1);
    public static final int NUMBER_OF_PREINSERTED_DEALS = 2;

    public static final ClientOrder PREINSERTED_CLIENT_ORDER_1 = new ClientOrder(1L, false, PaymentMethod.CASH, BigDecimal.TEN, true);
    public static final ClientOrderDTO PREINSERTED_CLIENT_ORDERDTO_1 = new ClientOrderDTO(1L, false, PaymentMethodDTO.CASH, BigDecimal.TEN, true, PREINSERTED_USERDTO_1);
    public static final ClientOrder PREINSERTED_CLIENT_ORDER_WITHOUT_ORDER_HISTORY = new ClientOrder(2L, false, PaymentMethod.CASH, BigDecimal.TEN, false);
    public static final int NUMBER_OF_PREINSERTED_CLIENT_ORDERS = 2;

    public static final OrderHistory PREINSERTED_ORDER_HISTORY_1 = new OrderHistory(1L, PREINSERTED_CLIENT_ORDER_1, LocalDateTime.of(2019, 1, 1, 0, 0), OrderState.PLACED);
    public static final OrderHistoryDTO PREINSERTED_ORDER_HISTORYDTO_1 = new OrderHistoryDTO(1L, PREINSERTED_CLIENT_ORDERDTO_1, LocalDateTime.of(2019, 1, 1, 0, 0), OrderStateDTO.PLACED);
    public static final int NUMBER_OF_PREINSERTED_ORDER_HISTORY = 1;

    public static final FeedbackMessage PREINSERTED_FEEDBACK_MESSAGE_1 = new FeedbackMessage(1L, "Feedback", "Good product", PREINSERTED_ORDER_HISTORY_1);
    public static final FeedbackMessageDTO PREINSERTED_FEEDBACK_MESSAGEDTO_1 = new FeedbackMessageDTO(1L, "Feedkback", "Good product", PREINSERTED_ORDER_HISTORYDTO_1);
    public static final int NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES = 1;

    public static final BoughtFurniture PREINSERTED_BOUGHT_FURNITURE_1 = new BoughtFurniture(1L, 1, BigDecimal.TEN);
    public static final BoughtFurnitureDTO PREINSERTED_BOUGHT_FURNITURE_DTO_1 = new BoughtFurnitureDTO(1L, PREINSERTED_FURNITUREDTO_1, 1, BigDecimal.TEN, PREINSERTED_CLIENT_ORDERDTO_1);
    public static final int NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE = 1;
}
