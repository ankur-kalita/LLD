public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return "Paid via UPI: " + amount;
    }
}
