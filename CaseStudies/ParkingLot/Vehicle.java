package CaseStudies.ParkingLot;

abstract class Vehicle {
    protected String licensePlate;
    protected PowerSource powerSource;

    public Vehicle(String plate, PowerSource powerSource) {
        this.licensePlate = plate;
        this.powerSource = powerSource;
    }

    public abstract String getType();
    public String getLicensePlate() { return licensePlate; }
    public PowerSource getPowerSource() { return powerSource; }
}
