package CaseStudies.ATM.bank;

import java.util.HashMap;
import java.util.Map;

public class RealBankService implements BankService {
    private final Map<String,Integer> balances = new HashMap<>();
    private final Map<String,Integer> dailySpent = new HashMap<>();
    private final Map<String,String> pins     = new HashMap<>();

    public RealBankService() {
        balances.put("4111111111111111", 5000);
        dailySpent.put("4111111111111111", 0);
        pins.put("4111111111111111", "1234");
    }

    @Override public PinDecision verifyPin(String pan, String pin) {
        if (!pins.containsKey(pan)) return PinDecision.CARD_BLOCKED;
        return pins.get(pan).equals(pin) ? PinDecision.OK : PinDecision.INVALID;
    }

    @Override public AmountDecision precheck(String pan, int amount) {
        return AmountDecision.OK; // keep simple; proxy will enforce policy
    }

    @Override public WithdrawalResult withdraw(String pan, int amount) {
        int bal = balances.getOrDefault(pan, 0);
        if (bal < amount) return new WithdrawalResult(WithdrawalResult.Status.INSUFFICIENT_FUNDS, bal);
        balances.put(pan, bal - amount);
        dailySpent.put(pan, dailySpent.getOrDefault(pan,0) + amount);
        return new WithdrawalResult(WithdrawalResult.Status.OK, balances.get(pan));
    }

    public int todaySpent(String pan){ return dailySpent.getOrDefault(pan, 0); }
}

