package CaseStudies.ParkingLot;

import java.util.List;

interface SpotAllocationStrategy {
    ParkingSpot findSpot(Vehicle vehicle, List<ParkingSpot> spots);
}
