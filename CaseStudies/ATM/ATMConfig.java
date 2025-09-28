package CaseStudies.ATM;

import java.util.List;

public final class ATMConfig {
    private static final ATMConfig INSTANCE = new ATMConfig();
    public static ATMConfig get() { return INSTANCE; }
    private ATMConfig() {}

    // limits / settings
    public final int pinMaxAttempts = 3;
    public final int minWithdrawal = 200;
    public final int maxWithdrawal = 10000;
    public final int dailyLimit    = 20000;

    // supported note denominations (highest first)
    public final List<Integer> denominations = List.of(2000, 500, 200, 100);
}
