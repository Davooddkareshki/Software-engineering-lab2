package services;

import constants.Notifier;
import models.Customer;

public class MessageService {
    void sendMessage(Customer customer, String message, Notifier notifier) {
        MessageSender emailSender = new EmailSender();
        emailSender.sendMessage(customer.getEmail(), message, notifier);

        MessageSender smsSender = new SmsSender();
        smsSender.sendMessage(customer.getMobile(), message, notifier);
    }
}
