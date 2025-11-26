package services;

public class SmsSender {
    public void sendSms(String number, String message){
        System.out.println("Sending SMS to " + number + ": " + message);
    }
}