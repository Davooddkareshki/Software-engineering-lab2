package services;

import constants.Notifier;

public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String to, String message, Notifier notifier){
        if (notifier != Notifier.EMAIL) return;
        if (to == null || to.isEmpty()) return;
        System.out.println("Sending email to " + to + ": " + message);
    }
}