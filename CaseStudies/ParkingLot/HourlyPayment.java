package CaseStudies.ParkingLot;

import java.time.*;

class HourlyPayment implements PaymentStrategy {
    private static final double RATE_PER_HOUR = 20.0;

    @Override
    public double calculateFee(Ticket ticket) {
        long hours = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toHours();
        if (hours == 0) hours = 1; 
        return (hours * RATE_PER_HOUR) + ticket.getVehicle().getPowerSource().getSurcharge();
    }
}