public class Demo01 {
    public static void main(String[] args) {
        TaxCalculator taxCalculator = new StandardTaxCalculator();
        NotificationService email = new EmailNotificationService();
        OrderService orderService = new OrderService(taxCalculator, email);
        orderService.checkout("inshallah.com", 100.0);
    }
}
