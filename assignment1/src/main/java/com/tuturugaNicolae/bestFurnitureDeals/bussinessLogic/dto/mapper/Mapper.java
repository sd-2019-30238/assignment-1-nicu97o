package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper;

public interface Mapper<T, D> {
    T convertToEntity(D d);

    D convertToDTO(T t);
}
