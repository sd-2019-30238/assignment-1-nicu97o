package com.bestfurnituredeals.assignment3.facade.command;

import com.bestfurnituredeals.assignment3.model.write.DealAddCommandDTO;

public interface DealCommandServiceFacade {
    void addDeal(DealAddCommandDTO dealAddCommandDTO, long furnitureId);

    void deleteDeal(long id);
}
