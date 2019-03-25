package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.impl;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.GenericDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
    private Class<T> clazz;
    @Autowired
    protected SessionFactory sessionFactory;

    public GenericDAOImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> selectAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Override
    public T selectById(long id) {
        return sessionFactory.getCurrentSession().get(clazz, id);
    }

    @Override
    public void insert(T t) {
        sessionFactory.openSession().merge(t);
    }

    @Override
    public void update(T t) {
        sessionFactory.getCurrentSession().saveOrUpdate(t);
    }

    @Override
    public void delete(T t) {
        sessionFactory.getCurrentSession().delete(t);
    }
}
