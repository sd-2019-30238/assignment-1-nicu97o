package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.mapper;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.FurnitureMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import org.junit.Before;
import org.junit.Test;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FurnitureMapperTest {
    private Mapper<Furniture, FurnitureDTO> mapper;

    @Before
    public void setUp() {
        mapper = new FurnitureMapper();
    }

    @Test
    public void testConvertToEntity_shouldBeSuccessful() {
        Furniture furniture = mapper.convertToEntity(PREINSERTED_FURNITUREDTO_1);
        assertThat(furniture, is(PREINSERTED_FURNITURE_1));
    }

    @Test
    public void testConvertToDTO_shouldBeSuccessful() {
        FurnitureDTO furnitureDTO = mapper.convertToDTO(PREINSERTED_FURNITURE_1);
        assertThat(furnitureDTO, is(PREINSERTED_FURNITUREDTO_1));
    }
}
