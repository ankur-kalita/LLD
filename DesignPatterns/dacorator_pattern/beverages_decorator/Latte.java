package dacorator_pattern;

public class Latte extends Beverage{
    public Latte() {
        description = "Latte Coffee";
    }
    @Override
    public double cost() {
        return 1.49;
    }
    
}
