package CaseStudies.ATM;


import CaseStudies.ATM.bank.BankService;
import CaseStudies.ATM.bank.BankServiceProxy;
import CaseStudies.ATM.bank.RealBankService;
import CaseStudies.ATM.mock.MockHW.*;
import CaseStudies.ATM.strategy.DispenseStrategy;

public class Main {
    public static void main(String[] args) {
        var display   = new Disp();
        var keypad    = new Keys();
        var reader    = new Reader();
        var dispenser = new Cash();
        var printer   = new Printer();

        BankService bank = new BankServiceProxy(new RealBankService());              // Proxy
        var strategy = DispenseStrategy.greedy();                                   // Strategy

        var atm = new ATM(display, keypad, reader, dispenser, printer, bank, strategy);

        // Run a happy path
        atm.insertCard();
        atm.onPinEntered(keypad.readPin());
        atm.onAmountEntered(keypad.readAmount());
    }
}

