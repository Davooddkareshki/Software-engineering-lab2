package services;

public class InvoicePrinter {
    public void printInvoice(Reservation reservation) {
        System.out.println("----- INVOICE -----");
        System.out.println("Customer: " + reservation.getCustomer().getName());
        System.out.println("Room: " + reservation.getRoom().getNumber() + " (" + reservation.getRoom().getType() + ")");
        System.out.println("Total: " + reservation.totalPrice());
        System.out.println("-------------------");
    }
}
