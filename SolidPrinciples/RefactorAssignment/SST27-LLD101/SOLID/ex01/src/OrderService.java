public class OrderService {
    private TaxCalculator taxCalculator;
    private NotificationService notificationService;

    public OrderService(TaxCalculator taxCalculator, NotificationService notificationService) {
        this.taxCalculator = taxCalculator;
        this.notificationService = notificationService;
    }

    void checkout(String customerEmail, double subtotal) {
        double tax = taxCalculator.calculateTax(subtotal);
        double total = subtotal + tax;
        notificationService.notify(customerEmail, "Thanks! Your total is " + total);
        System.out.println("Order stored (pretend DB).");
    }
}