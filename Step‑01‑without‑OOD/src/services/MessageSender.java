package services;

import constants.Notifier;

public interface MessageSender {
    public void sendMessage(String to, String message, Notifier notifier);
}