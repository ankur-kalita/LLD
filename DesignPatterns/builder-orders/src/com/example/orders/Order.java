package com.example.orders;

import java.util.ArrayList;
import java.util.List;

/**
 * Telescoping constructors + setters. Allows invalid states.
 */
public class Order {
    private final String id;
    private final String customerEmail;
    private final List<OrderLine> lines;
    private final Integer discountPercent; // 0..100 expected, but not enforced
    private final boolean expedited;
    private final String notes;

    private Order(Builder builder) {
        this.id = builder.id;
        this.customerEmail = builder.customerEmail;
        List<OrderLine> copy = new ArrayList<>();
        for (OrderLine l: builder.lines) {
            copy.add(new OrderLine(l.getSku(), l.getQuantity() ,l.getUnitPriceCents()));
        }
        this.lines = List.copyOf(copy); // help creating an unmodifiable list
        this.discountPercent = builder.discountPercent;
        this.expedited = builder.expedited;
        this.notes = builder.notes;
    }

    public String getId() { return id; }
    public String getCustomerEmail() { return customerEmail; }
    public List<OrderLine> getLines() { return lines; } // leaks internal list // not now
    public Integer getDiscountPercent() { return discountPercent; }
    public boolean isExpedited() { return expedited; }
    public String getNotes() { return notes; }

    public static final class Builder {
        private String id;
        private String customerEmail;
        private List<OrderLine> lines = new ArrayList<>();
        private Integer discountPercent; // 0..100 expected, but not enforced
        private boolean expedited;
        private String notes;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.customerEmail = email;
            return this;
        }

        public Builder addLine(String sku, int qty, int unitPriceCents) {
            lines.add(new OrderLine(sku, qty, unitPriceCents));
            return this;
        }

        public Builder discount(Integer discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder expedited(boolean expedited) {
            this.expedited = expedited;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Order build() {
            if (id == null || id.isBlank()) throw new IllegalStateException("id is required");
            if (!PricingRules.isValidEmail(customerEmail)) throw new IllegalStateException("email is required");
            if (!PricingRules.isValidDiscount(discountPercent)) throw new IllegalStateException("discount must be 0..100");
            return new Order(this);
        }
    }

    public int totalBeforeDiscount() {
        int sum = 0;
        for (OrderLine l : lines) sum += l.getQuantity() * l.getUnitPriceCents();
        return sum;
    }

    public int totalAfterDiscount() {
        int base = totalBeforeDiscount();
        if (discountPercent == null) return base;
        return base - (base * discountPercent / 100);
    }
}
