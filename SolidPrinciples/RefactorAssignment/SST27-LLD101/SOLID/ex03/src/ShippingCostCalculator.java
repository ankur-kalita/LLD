public class ShippingCostCalculator {
    private ShippingRate[] shippingRates;

    public ShippingCostCalculator() {
        shippingRates = new ShippingRate[] {
            new StandardShippingRate(),
            new ExpressShippingRate(),
            new OvernightShippingRate()
        };
    }

    public double cost(String shippingType, double weightKg) {
        for (ShippingRate rate : shippingRates) {
            if (rate.supports(shippingType)) {
                return rate.calculate(weightKg);
            }
        }
        throw new IllegalArgumentException("Unsupported shipping type: " + shippingType);
    }
}
