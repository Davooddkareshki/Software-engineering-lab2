package services;

import constants.Notifier;
import models.Customer;

public class SmsSender implements MessageSender {
    @Override
    public void sendMessage(Customer customer, String message) {
        if (customer.getMobile() == null) {return;}
        System.out.println("Sending SMS to " + customer.getMobile() + ": " + message);
    }
}