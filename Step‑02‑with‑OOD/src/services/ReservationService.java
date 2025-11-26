package services;

public class ReservationService {
    private final PaymentStrategy paymentStrategy;
    private final NotificationService notificationService;
    private final InvoicePrinter invoicePrinter;

    public ReservationService(PaymentStrategy paymentStrategy,
                              NotificationService notificationService,
                              InvoicePrinter invoicePrinter) {
        this.paymentStrategy = paymentStrategy;
        this.notificationService = notificationService;
        this.invoicePrinter = invoicePrinter;
    }

    public void makeReservation(Reservation res) {
        System.out.println("Customer: " + res.getCustomerName());

        if(res.getCustomer().getCity().equals("Paris")){
            System.out.println("Apply city discount for Paris!");
            res.getRoom().discountPrice();
        }

        paymentStrategy.pay(res.totalPrice());

        invoicePrinter.printInvoice(res);

        notificationService.sendNotification(res.getCustomer(), "Your reservation confirmed!");
    }
}
