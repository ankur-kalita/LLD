package dacorator_pattern;

public class Cuppuccino extends Beverage{
    public Cuppuccino() {
        description = "Cuppuccino Coffee";
    }
    @Override
    public double cost() {
        return 1.99;
    }
}