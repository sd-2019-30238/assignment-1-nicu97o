package com.bestfurnituredeals.assignment3.service.command;

import com.bestfurnituredeals.assignment3.model.database.Deal;

public interface DealCommandService {
    void addDeal(Deal deal, long furnitureId);

    void deleteDeal(long id);

    void updateDeal(Deal deal);
}
