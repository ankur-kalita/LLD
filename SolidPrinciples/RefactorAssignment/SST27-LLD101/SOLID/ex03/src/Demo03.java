public class Demo03 {
    public static void main(String[] args) {
        ShippingCostCalculator calculator = new ShippingCostCalculator();
        System.out.println("Standard Shipping Cost: " + calculator.cost("EXPRESS", 10));
    }
}
