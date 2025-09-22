package CaseStudies.ParkingLot;

class EV implements PowerSource {
    @Override
    public double getSurcharge() { 
        return 50.0; 
    } 
    @Override
    public String getType() { 
        return "EV"; 
    }
}
