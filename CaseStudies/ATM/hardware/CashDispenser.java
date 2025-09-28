package CaseStudies.ATM.hardware;

import java.util.Map;

public interface CashDispenser {
    boolean canDispense(int amount);
    void dispense(Map<Integer,Integer> notes); // denom -> count
}

