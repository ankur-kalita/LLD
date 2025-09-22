package CaseStudies.ParkingLot;

class Combustion implements PowerSource {
    @Override
    public double getSurcharge() { return 0.0; }
    @Override
    public String getType() { return "Combustion"; }
}
