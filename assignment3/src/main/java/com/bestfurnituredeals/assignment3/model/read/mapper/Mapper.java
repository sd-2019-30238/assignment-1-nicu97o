package com.bestfurnituredeals.assignment3.model.read.mapper;

public interface Mapper<T, D> {
    T convertToEntity(D d);

    D convertToDTO(T t);
}
