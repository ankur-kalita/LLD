package CaseStudies.ParkingLot;

class CarSpot extends ParkingSpot {
    public CarSpot(String id) { 
        super(id); 
    }
    @Override 
    public String getSpotType() { 
        return "CarSpot"; 
    }
}
