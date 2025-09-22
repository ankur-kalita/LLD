package CaseStudies.ParkingLot;

import java.time.*;

class DailyPayment implements PaymentStrategy {
    private static final double RATE_PER_DAY = 100.0;

    @Override
    public double calculateFee(Ticket ticket) {
        long days = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toDays();
        if (days == 0) days = 1; 
        return (days * RATE_PER_DAY) + ticket.getVehicle().getPowerSource().getSurcharge();
    }
}
