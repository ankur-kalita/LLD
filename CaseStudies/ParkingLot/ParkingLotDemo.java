package CaseStudies.ParkingLot;

import java.util.*;

public class ParkingLotDemo {
    public static void main(String[] args) throws InterruptedException {
        // Create spots
        List<ParkingSpot> spotsLevel1 = Arrays.asList(
                new CarSpot("C1"), new BikeSpot("B1"), new TruckSpot("T1")
        );

        Level level1 = new Level(1, spotsLevel1);
        ParkingLot lot = ParkingLot.getInstance(Arrays.asList(level1), new NearestSpotStrategy());

        // Vehicles
        Vehicle evCar = new Car("EV123", new EV());
        Vehicle petrolBike = new Bike("BK001", new Combustion());

        // Park EV Car
        Ticket ticket1 = lot.parkVehicle(evCar);
        Thread.sleep(1000); // simulate time
        double fee1 = lot.unparkVehicle(ticket1, new HourlyPayment());
        System.out.println("Fee for EV Car: " + fee1);

        // Park Combustion Bike
        Ticket ticket2 = lot.parkVehicle(petrolBike);
        Thread.sleep(1000); // simulate time
        double fee2 = lot.unparkVehicle(ticket2, new DailyPayment());
        System.out.println("Fee for Combustion Bike: " + fee2);
    }
}
