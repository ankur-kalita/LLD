package CaseStudies.ATM.bank;


import CaseStudies.ATM.*;

public class BankServiceProxy implements BankService {
    private final RealBankService real;

    public BankServiceProxy(RealBankService real){ this.real = real; }

    @Override public PinDecision verifyPin(String pan, String pin) {
        // cross-cutting concerns (logging/throttle/etc.) can live here
        return real.verifyPin(pan, pin);
    }

    @Override public AmountDecision precheck(String pan, int amount) {
        var cfg = ATMConfig.get();
        if (amount < cfg.minWithdrawal) return AmountDecision.BELOW_MIN;
        if (amount > cfg.maxWithdrawal) return AmountDecision.ABOVE_MAX;
        int spent = real.todaySpent(pan);
        if (spent + amount > cfg.dailyLimit) return AmountDecision.EXCEEDS_DAILY_LIMIT;
        return real.precheck(pan, amount);
    }

    @Override public WithdrawalResult withdraw(String pan, int amount) {
        // could add network retries/metrics/etc.
        return real.withdraw(pan, amount);
    }
}
