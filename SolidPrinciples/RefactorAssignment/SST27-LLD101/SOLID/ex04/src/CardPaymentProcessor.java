public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return "Charged card: " + amount;
    }
    
}
