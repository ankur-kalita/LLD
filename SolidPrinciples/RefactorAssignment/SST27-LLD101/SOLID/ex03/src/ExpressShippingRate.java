public class ExpressShippingRate implements ShippingRate {
    public double calculate(double weightKg) {
        return 80 + 8 * weightKg;
    }
    public boolean supports(String shippingType) {
        return "EXPRESS".equalsIgnoreCase(shippingType);
    }
    
}
