package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import java.util.List;

public interface GenericDAO<T> {
    List<T> selectAll();

    T selectById(long id);

    void insert(T t);

    void update(T t);

    void delete(T t);
}
