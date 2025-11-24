package services;

import constants.Notifier;
import constants.PaymentMethods;

public class ReservationService {
    private Notifier notifier = Notifier.EMAIL; //default Notifier
    private PaymentProcessor paymentProcessor = new PaymentProcessor();

    public void makeReservation(Reservation res, PaymentMethods paymentType, Notifier notifier){
        System.out.println("Processing reservation for " + res.getCustomer().getName());

        if(res.getCustomer().getCity().equals("Paris")){
            System.out.println("Apply city discount for Paris!");
            res.getRoom().discountPrice();
        }

        switch (paymentType){
            case CARD:
                paymentProcessor.payByCard(res.totalPrice());
                break;
            case PAYPAL:
                paymentProcessor.payByPayPal(res.totalPrice());
                break;
            case CASH:
                paymentProcessor.payByCash(res.totalPrice());
                break;
        }

        System.out.println("----- INVOICE -----");
        System.out.println("hotel.Customer: " + res.getCustomer().getName());
        System.out.println("hotel.Room: " + res.getRoom().getNumber() + " (" + res.getRoom().getType() + ")");
        System.out.println("Total: " + res.totalPrice());
        System.out.println("-------------------");

        MessageService messageService = new MessageService();
        messageService.sendMessage(res.getCustomer(),"Your reservation confirmed!", this.notifier);

        switch (this.notifier){

            case EMAIL:
                break;

            case SMS:
                break;
            default:
                System.out.println("There is no Message Provider");
        }
    }
}