public class StandardShippingRate implements ShippingRate {
    public double calculate(double weightKg) {
        return 50 + 5 * weightKg;
    }
    public boolean supports(String shippingType) {
        return "STANDARD".equalsIgnoreCase(shippingType);
    }
}
