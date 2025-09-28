package CaseStudies.ParkingLot;

abstract class TicketDecorator extends Ticket {
    protected Ticket ticket;

    public TicketDecorator(Ticket ticket) {
        super(ticket.getVehicle(), ticket.getSpot());
        this.ticket = ticket;
    }

    public abstract double calculateExtraFee();
}