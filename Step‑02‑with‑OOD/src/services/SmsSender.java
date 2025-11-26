package services;

import constants.Notifier;

public class SmsSender implements MessageSender {
    @Override
    public void sendMessage(String to, String message, Notifier notifier){
        if (notifier != Notifier.SMS) return;
        if (to == null || to.isEmpty()) return;
        System.out.println("Sending SMS to " + to + ": " + message);
    }
}