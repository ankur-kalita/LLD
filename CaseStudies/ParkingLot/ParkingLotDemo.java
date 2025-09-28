package CaseStudies.ParkingLot;

import java.util.*;

public class ParkingLotDemo {
    public static void main(String[] args) throws InterruptedException {
        List<ParkingSpot> spotsLevel1 = Arrays.asList(
                new CarSpot("C1"), new BikeSpot("B1"), new TruckSpot("T1")
        );

        Level level1 = new Level(1, spotsLevel1);
        ParkingLot lot = ParkingLot.getInstance(Arrays.asList(level1), new NearestSpotStrategy());

        Vehicle evCar = new Car("EV123", new EV());
        Vehicle petrolBike = new Bike("BK001", new Combustion());

        Ticket ticket1 = lot.parkVehicle(evCar);
        Thread.sleep(1000); 
        // double fee1 = lot.unparkVehicle(ticket1, new HourlyPayment());
        Ticket cleaningTicket = new CleaningDecorator(ticket1);

        PaymentStrategy payment = new CleaningPayment(new HourlyPayment());
        double totalFee = payment.calculateFee(cleaningTicket);

        System.out.println("Total fee with cleaning: " + totalFee);
        // System.out.println("Fee for EV Car: " + fee1);

        Ticket ticket2 = lot.parkVehicle(petrolBike);
        Thread.sleep(1000); 
        double fee2 = lot.unparkVehicle(ticket2, new DailyPayment());
        System.out.println("Fee for Combustion Bike: " + fee2);
    }
}
