package CaseStudies.ParkingLot;

class BikeSpot extends ParkingSpot {
    public BikeSpot(String id) { 
        super(id); 
    }
    @Override 
    public String getSpotType() { 
        return "BikeSpot"; 
    }
}
