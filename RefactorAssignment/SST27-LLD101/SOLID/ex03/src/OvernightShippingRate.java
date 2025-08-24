public class OvernightShippingRate implements ShippingRate {
    public double calculate(double weightKg) {
        return 100 + 10 * weightKg;
    }
    public boolean supports(String shippingType) {
        return "OVERNIGHT".equalsIgnoreCase(shippingType);
    }
    
}
