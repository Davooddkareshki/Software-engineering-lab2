package services;

import constants.Notifier;

public interface MessageSender {
    void sendMessage(String to, String message, Notifier notifier);
}