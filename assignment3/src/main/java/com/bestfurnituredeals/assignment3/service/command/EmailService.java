package com.bestfurnituredeals.assignment3.service.command;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMail(String mailOfTheReceiver, String subject, String message) throws MessagingException;
}
