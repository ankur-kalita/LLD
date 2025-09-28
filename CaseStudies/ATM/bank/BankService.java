package CaseStudies.ATM.bank;


public interface BankService {
    PinDecision verifyPin(String pan, String pin);
    AmountDecision precheck(String pan, int amount);
    WithdrawalResult withdraw(String pan, int amount);
}

