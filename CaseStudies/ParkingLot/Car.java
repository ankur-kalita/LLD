package CaseStudies.ParkingLot;

class Car extends Vehicle {
    public Car(String plate, PowerSource powerSource) { super(plate, powerSource); }
    @Override public String getType() { return "Car"; }
}
