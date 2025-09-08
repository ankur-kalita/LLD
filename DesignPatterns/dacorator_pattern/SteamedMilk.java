package dacorator_pattern;

public class SteamedMilk extends CondimentDecorator {
    public SteamedMilk(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription() {
        return beverage.getDescription() + ", SteamedMilk";
    }
    public double cost() {
        return beverage.cost() + 0.10;
    }
}
