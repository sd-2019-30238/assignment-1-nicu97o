package com.bestfurnituredeals.assignment3.service.command.impl;

import com.bestfurnituredeals.assignment3.dao.ClientOrderDAO;
import com.bestfurnituredeals.assignment3.dao.UserObserverDAO;
import com.bestfurnituredeals.assignment3.exception.NoClientOrderFoundException;
import com.bestfurnituredeals.assignment3.model.database.ClientOrder;
import com.bestfurnituredeals.assignment3.model.database.UserObserver;
import com.bestfurnituredeals.assignment3.service.command.EmailService;
import com.bestfurnituredeals.assignment3.service.command.ObserverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
@Transactional
public class ObserverServiceImpl implements ObserverService<UserObserver, ClientOrder> {
    private ClientOrderDAO clientOrderDAO;
    private UserObserverDAO userObserverDAO;
    private EmailService emailService;

    @Autowired
    public ObserverServiceImpl(ClientOrderDAO clientOrderDAO, UserObserverDAO userObserverDAO, EmailService emailService) {
        this.clientOrderDAO = clientOrderDAO;
        this.userObserverDAO = userObserverDAO;
        this.emailService = emailService;
    }

    @Override
    public void addObserver(long observableId, UserObserver userObserver) {
        ClientOrder observable = clientOrderDAO.findById(observableId).orElseThrow(() -> new NoClientOrderFoundException());
        observable.getObservers().add(userObserver);
        userObserver.setObservable(observable);
        clientOrderDAO.save(observable);
    }

    @Override
    public void removeObserver(long observableId, UserObserver userObserver) {
        ClientOrder observable = clientOrderDAO.findById(observableId).orElseThrow(() -> new NoClientOrderFoundException());
        for (UserObserver observer : observable.getObservers()) {
            if (observer.getMail().equals(userObserver.getMail())) {
                userObserverDAO.delete(observer);
            }
        }
    }

    @Override
    public void notifyObservers(long observableId) throws MessagingException {
        ClientOrder observable = clientOrderDAO.findById(observableId).orElseThrow(() -> new NoClientOrderFoundException());
        for (UserObserver userObserver : observable.getObservers()) {
            emailService.sendMail(userObserver.getMail(), "Order update", "Order state of order " + observable.getId() + " changed to " +
                    observable.getOrderHistory().getOrderState());
        }
    }
}
