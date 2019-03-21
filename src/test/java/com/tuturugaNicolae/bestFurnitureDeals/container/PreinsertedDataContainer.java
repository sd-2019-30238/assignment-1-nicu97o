package com.tuturugaNicolae.bestFurnitureDeals.container;

import com.tuturugaNicolae.bestFurnitureDeals.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class PreinsertedDataContainer {
    public static final User PREINSERTED_USER_1 = new User(1L, "tuturuga", "12345", "tuturuganicu@gmail.com", UserType.CLIENT);
    public static final User PREINSERTED_USER_2 = new User(2L, "nicu", "abcdefg", "nicu.tuturuga@yahoo.com", UserType.STAFF);
    public static final int NUMBER_OF_PREINSERTED_USERS = 2;

    public static final Furniture PREINSERTED_FURNITURE_1 = new Furniture(1L, "Chair", "Strong material");
    public static final int NUMBER_OF_PREINSERTED_FURNITURE = 1;

    public static final Deal PREINSERTED_DEAL_1 = new Deal(1L, "Chair", DealType.REDUCED, PREINSERTED_FURNITURE_1, BigDecimal.TEN, true, 1);
    public static final Deal PREINSERTED_DEAL_2 = new Deal(2L, "Chair 2", DealType.REDUCED, PREINSERTED_FURNITURE_1, BigDecimal.ONE, true, 1);
    public static final int NUMBER_OF_PREINSERTED_DEALS = 2;

    public static final FeedbackMessage PREINSERTED_FEEDBACK_MESSAGE_1 = new FeedbackMessage(1L, "Feedback", "Good product");
    public static final int NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES = 1;

    public static final BoughtFurniture PREINSERTED_BOUGHT_FURNITURE_1 = new BoughtFurniture(1L, 1, BigDecimal.TEN);
    public static final int NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE = 1;

    public static final ClientOrder PREINSERTED_CLIENT_ORDER_1 = new ClientOrder(1L, false, PaymentMethod.CASH, BigDecimal.TEN, true);
    public static final ClientOrder PREINSERTED_CLIENT_ORDER_WITHOUT_ORDER_HISTORY = new ClientOrder(2L, false, PaymentMethod.CASH, BigDecimal.TEN, false);
    public static final int NUMBER_OF_PREINSERTED_CLIENT_ORDERS = 2;

    public static final OrderHistory PREINSERTED_ORDER_HISTORY_1 = new OrderHistory(1L, LocalDateTime.of(2019, 1, 1, 0, 0), OrderState.PLACED);
    public static final int NUMBER_OF_PREINSERTED_ORDER_HISTORY = 1;
}
