package CaseStudies.ParkingLot;

import java.util.*;

class Level {
    private final int levelNumber;
    private final List<ParkingSpot> spots;

    public Level(int levelNumber, List<ParkingSpot> spots) {
        this.levelNumber = levelNumber;
        this.spots = spots;
    }

    public ParkingSpot findAvailableSpot(Vehicle v, SpotAllocationStrategy strategy) {
        return strategy.findSpot(v, spots);
    }

    public boolean isFull() {
        return spots.stream().noneMatch(ParkingSpot::isFree);
    }

    public int getLevelNumber() { return levelNumber; }
}

