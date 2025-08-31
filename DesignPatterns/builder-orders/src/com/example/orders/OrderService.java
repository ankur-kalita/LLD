package com.example.orders;

import java.util.List;
import java.util.Objects;

public class OrderService {

    public Order createOrder(String id, String email, List<OrderLine> lines, Integer discount, boolean expedited, String notes) {
        // Order o = new Order(id, email, discount);
        // Order o = new Order.Builder().id(id).email(email).discount(discount).build();
        // // if (lines != null) for (OrderLine l : lines) o.addLine(l);
        // // o.setExpedited(expedited);
        // // o.setNotes(notes);
        // return o;

        Order.Builder b = new Order.Builder()
                .id(id)
                .email(email)
                .discount(discount)
                .expedited(expedited)
                .notes(notes);
        if (lines != null) {
            for (OrderLine l : lines) b.addLine(l.getSku(), l.getQuantity(), l.getUnitPriceCents());
        }
        return b.build();
    }
}
