package com.bestFurnitureDeals.dto.mapper;

public interface Mapper<T, D> {
    T convertToEntity(D d);

    D convertToDTO(T t);
}
