package services;

import constants.Notifier;
import models.Customer;

public interface MessageSender {
    void sendMessage(Customer customer, String message);
}