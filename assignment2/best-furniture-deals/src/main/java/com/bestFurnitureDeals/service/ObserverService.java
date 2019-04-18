package com.bestFurnitureDeals.service;

import com.bestFurnitureDeals.model.observer.Observable;
import com.bestFurnitureDeals.model.observer.Observer;

import javax.mail.MessagingException;

public interface ObserverService<T extends Observer, V extends Observable> {
    void addObserver(long observableId, T t);

    void removeObserver(long observableId, T t);

    void notifyObservers(long observableId) throws MessagingException;
}
