package services;

public class ReservationService {
    private final PaymentStrategy paymentStrategy;
    private final MessageSender messageSender;
    private final InvoicePrinter invoicePrinter;

    public ReservationService(PaymentStrategy paymentStrategy,
                              MessageSender messageSender,
                              InvoicePrinter invoicePrinter) {
        this.paymentStrategy = paymentStrategy;
        this.messageSender = messageSender;
        this.invoicePrinter = invoicePrinter;
    }

    public void makeReservation(Reservation res) {
        System.out.println("Customer: " + res.getCustomerName());

        if(res.getCustomerCity().equals("Paris")){
            System.out.println("Apply city discount for Paris!");
            res.applyDiscountOnRoom();
        }

        paymentStrategy.pay(res.totalPrice());

        invoicePrinter.printInvoice(res);

        messageSender.sendMessage(res.getCustomer(), "Your reservation confirmed!");
    }
}
