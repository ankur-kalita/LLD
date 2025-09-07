package com.example.payments;

public class SafeCashAdapter implements PaymentGateway {
    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = client;
    }

    public String charge(String customerID, int amountCents) {
        SafeCashPayment payment = client.createPayment(amountCents, customerID);
        return payment.confirm();
    }
}
