public class OrderService {
    private TaxCalculator taxCalculator;
    private NotificationService email;

    public OrderService(TaxCalculator taxCalculator, NotificationService email) {
        this.taxCalculator = taxCalculator;
        this.email = email;
    }

    void checkout(String customerEmail, double subtotal) {
        double tax = taxCalculator.calculateTax(subtotal);
        double total = subtotal + tax;
        email.notify(customerEmail, "Thanks! Your total is " + total);
        System.out.println("Order stored (pretend DB).");
    }
}