public class StandardTaxCalculator implements TaxCalculator {
    public double getTaxRate() {
        return 0.18;
    }

    public double calculateTax(double amount) {
        double taxRate = getTaxRate();
        return amount * taxRate;
    }
    
}
