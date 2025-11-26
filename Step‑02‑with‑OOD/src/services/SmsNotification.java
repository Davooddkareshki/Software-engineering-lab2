package services;

import models.Customer;

public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification(Customer customer, String message) {
        System.out.println("SMS sent to " + customer.getMobile() + " : " + message);
    }
}
