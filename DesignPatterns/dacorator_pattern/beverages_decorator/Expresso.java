package dacorator_pattern;

public class Expresso extends Beverage{
    public Expresso() {
        description = "Expresso";
    }
    @Override
    public double cost() {
        return 1.99;
    }
}
