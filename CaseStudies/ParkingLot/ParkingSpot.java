package CaseStudies.ParkingLot;

abstract class ParkingSpot {
    private final String id;
    private Vehicle currentVehicle;

    public ParkingSpot(String id) { this.id = id; }

    public boolean isFree() { return currentVehicle == null; }
    public void assignVehicle(Vehicle v) { this.currentVehicle = v; }
    public void freeSpot() { this.currentVehicle = null; }
    public String getId() { return id; }

    public abstract String getSpotType();
}