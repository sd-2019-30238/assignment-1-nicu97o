package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.mapper;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.BoughtFurnitureMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.ClientOrderMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.FurnitureMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl.UserMapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import org.junit.Before;
import org.junit.Test;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BoughtFurnitureMapperTest {
    private Mapper<BoughtFurniture, BoughtFurnitureDTO> boughtFurnitureMapper;
    private Mapper<Furniture, FurnitureDTO> furnitureMapper;
    private Mapper<User, UserDTO> userMapper;
    private Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper;

    @Before
    public void setUp() {
        userMapper = new UserMapper();
        furnitureMapper = new FurnitureMapper();
        clientOrderMapper = new ClientOrderMapper(userMapper);
        boughtFurnitureMapper = new BoughtFurnitureMapper(clientOrderMapper, furnitureMapper);
    }

    @Test
    public void testConvertToEntity_shouldBeSuccessful() {
        BoughtFurniture boughtFurniture = boughtFurnitureMapper.convertToEntity(PREINSERTED_BOUGHT_FURNITURE_DTO_1);
        assertThat(boughtFurniture, is(PREINSERTED_BOUGHT_FURNITURE_1));
    }

    @Test
    public void testConvertToDTO_shouldBeSuccessful() {
        BoughtFurnitureDTO boughtFurnitureDTO = boughtFurnitureMapper.convertToDTO(PREINSERTED_BOUGHT_FURNITURE_1);
        assertThat(boughtFurnitureDTO, is(PREINSERTED_BOUGHT_FURNITURE_DTO_1));
    }
}
