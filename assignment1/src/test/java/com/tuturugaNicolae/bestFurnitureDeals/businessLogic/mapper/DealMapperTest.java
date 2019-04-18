package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.mapper;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.DealMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.FurnitureMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import org.junit.Before;
import org.junit.Test;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_DEALDTO_1;
import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_DEAL_1;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DealMapperTest {
    private Mapper<Deal, DealDTO> dealMapper;
    private Mapper<Furniture, FurnitureDTO> furnitureMapper;

    @Before
    public void setUp() {
        furnitureMapper = new FurnitureMapper();
        dealMapper = new DealMapper(furnitureMapper);
    }

    @Test
    public void testConvertToEntity_shouldBeSuccessful() {
        Deal deal = dealMapper.convertToEntity(PREINSERTED_DEALDTO_1);
        assertThat(deal, is(PREINSERTED_DEAL_1));
    }

    @Test
    public void testConvertToDTO_shouldBeSuccessful() {
        DealDTO dealDTO = dealMapper.convertToDTO(PREINSERTED_DEAL_1);
        assertThat(dealDTO, is(PREINSERTED_DEALDTO_1));
    }
}
