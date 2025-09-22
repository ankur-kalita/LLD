import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    private Map<String, PaymentProcessor> processors;

    public PaymentService() {
        processors = new HashMap<>();
        processors.put("CARD", new CardPaymentProcessor());
        processors.put("UPI", new UpiPaymentProcessor());
        processors.put("WALLET", new WalletPaymentProcessor());
    }
    
    public String pay(Payment payment) {
        PaymentProcessor processor = processors.get(payment.provider);
        if (processor == null) {
            throw new IllegalArgumentException("Unsupported payment method");
        }
        return processor.processPayment(payment.amount);
    }

    public void registerPaymentProcessor(String provider, PaymentProcessor processor) {
        processors.put(provider, processor);
    }
}