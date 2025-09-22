public interface ShippingRate {
    double calculate(double weightKg);
    boolean supports(String shippingType);
}
