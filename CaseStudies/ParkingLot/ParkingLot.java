package CaseStudies.ParkingLot;

import java.util.*;

class ParkingLot {
    private static ParkingLot instance;
    private final List<Level> levels;
    private final SpotAllocationStrategy strategy;

    private ParkingLot(List<Level> levels, SpotAllocationStrategy strategy) {
        this.levels = levels;
        this.strategy = strategy;
    }

    public static ParkingLot getInstance(List<Level> levels, SpotAllocationStrategy strategy) {
        if (instance == null) {
            instance = new ParkingLot(levels, strategy);
        }
        return instance;
    }

    public Ticket parkVehicle(Vehicle v) {
        for (Level level : levels) {
            ParkingSpot spot = level.findAvailableSpot(v, strategy);
            if (spot != null) {
                spot.assignVehicle(v);
                return new Ticket(v, spot);
            }
        }
        throw new RuntimeException("No parking spot available for vehicle: " + v.getType());
    }

    public double unparkVehicle(Ticket ticket, PaymentStrategy paymentStrategy) {
        ticket.closeTicket();
        ticket.getSpot().freeSpot();
        return paymentStrategy.calculateFee(ticket);
    }
}
