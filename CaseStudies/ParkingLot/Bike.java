package CaseStudies.ParkingLot;

class Bike extends Vehicle {
    public Bike(String plate, PowerSource powerSource) {
        super(plate, powerSource);
    }
    @Override 
    public String getType() {
         return "Bike"; 
    }
}
