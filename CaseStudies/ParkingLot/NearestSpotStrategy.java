package CaseStudies.ParkingLot;

import java.util.List;

class NearestSpotStrategy implements SpotAllocationStrategy {
    @Override
    public ParkingSpot findSpot(Vehicle vehicle, List<ParkingSpot> spots) {
        for (ParkingSpot spot : spots) {
            if (spot.isFree()) return spot;
        }
        return null; 
    }
}

// use tree set
