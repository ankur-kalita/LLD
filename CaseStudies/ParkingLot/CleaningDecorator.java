package CaseStudies.ParkingLot;

class CleaningDecorator extends TicketDecorator {
    private static final double CLEANING_FEE = 30.0;

    public CleaningDecorator(Ticket ticket) {
        super(ticket);
    }

    @Override
    public double calculateExtraFee() {
        return CLEANING_FEE;
    }
}
