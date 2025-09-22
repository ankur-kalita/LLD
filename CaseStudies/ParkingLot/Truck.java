package CaseStudies.ParkingLot;

class Truck extends Vehicle {
    public Truck(String plate, PowerSource powerSource) { 
        super(plate, powerSource); 
    }
    @Override public String getType() { 
        return "Truck"; 
    }
}