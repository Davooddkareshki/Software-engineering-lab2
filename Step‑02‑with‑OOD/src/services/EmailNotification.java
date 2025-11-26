package services;

import models.Customer;

public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(Customer customer, String message) {
        System.out.println("Email sent to " + customer.getEmail() + " : " + message);
    }
}
