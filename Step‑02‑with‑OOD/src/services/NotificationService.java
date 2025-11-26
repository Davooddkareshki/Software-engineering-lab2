package services;

import models.Customer;

public interface NotificationService {
    void sendNotification(Customer customer, String message);
}
