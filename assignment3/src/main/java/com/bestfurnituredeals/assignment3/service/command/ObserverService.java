package com.bestfurnituredeals.assignment3.service.command;


import com.bestfurnituredeals.assignment3.model.database.observer.Observable;
import com.bestfurnituredeals.assignment3.model.database.observer.Observer;

import javax.mail.MessagingException;

public interface ObserverService<T extends Observer, V extends Observable> {
    void addObserver(long observableId, T t);

    void removeObserver(long observableId, T t);

    void notifyObservers(long observableId) throws MessagingException;
}
