package CaseStudies.ParkingLot;

class TruckSpot extends ParkingSpot {
    public TruckSpot(String id) { 
        super(id); 
    }
    @Override 
    public String getSpotType() { 
        return "TruckSpot"; 
    }
}
