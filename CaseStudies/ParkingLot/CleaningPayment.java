package CaseStudies.ParkingLot;

class CleaningPayment implements PaymentStrategy {
    private PaymentStrategy basePayment;

    public CleaningPayment(PaymentStrategy basePayment) {
        this.basePayment = basePayment;
    }

    @Override
    public double calculateFee(Ticket ticket) {
        double baseFee = basePayment.calculateFee(ticket);
        if (ticket instanceof TicketDecorator t) {
            return baseFee + ((TicketDecorator) ticket).calculateExtraFee();
        }
        return baseFee;
    }
}
