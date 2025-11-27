import models.Customer;
import models.LuxuryRoom;
import services.Reservation;
import models.Room;
import services.ReservationService;

import services.*;

public class Main {
    public static void main(String[] args){
        Customer customer = new Customer("Ali", "ali@example.com","09124483765", "Paris");
        Room room = new LuxuryRoom("203", 150);
        Reservation res = new Reservation(room, customer, 2);

        PaymentStrategy myPayment = new CardPayment();
        MessageSender myMessageSender = new SmsSender();
        InvoicePrinter myPrinter = new InvoicePrinter();

        ReservationService service = new ReservationService(myPayment, myMessageSender, myPrinter);

        service.makeReservation(res);

        System.out.println("\n--- OnSite Payment ---");

        ReservationService service2 = new ReservationService(new CashPayment(), new EmailSender(), myPrinter);
        service2.makeReservation(res);
    }
}
