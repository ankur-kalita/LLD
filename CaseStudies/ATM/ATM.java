package CaseStudies.ATM;


import CaseStudies.ATM.bank.*;
import CaseStudies.ATM.domain.Card;
import CaseStudies.ATM.hardware.*;
import CaseStudies.ATM.pin.*;
import CaseStudies.ATM.strategy.DispenseStrategy;

import java.util.Map;

public class ATM {
    public enum Stage { IDLE, AWAIT_PIN, AWAIT_AMOUNT, DISPENSING, EJECTING }

    public final Display display;
    public final Keypad keypad;
    public final CardReader reader;
    public final CashDispenser dispenser;
    public final ReceiptPrinter printer;
    public final BankService bank;

    public final DispenseStrategy strategy; // Strategy pattern

    public Stage stage = Stage.IDLE;
    public Card currentCard;

    public ATM(Display d, Keypad k, CardReader r, CashDispenser c, ReceiptPrinter p,
               BankService bank, DispenseStrategy strategy) {
        this.display = d; this.keypad = k; this.reader = r; this.dispenser = c;
        this.printer = p; this.bank = bank; this.strategy = strategy;
    }

    // --- flow ---
    public void insertCard() {
        String pan = reader.readPAN();
        currentCard = new Card(pan);
        stage = Stage.AWAIT_PIN;
        new PinContext(this).setState(new AwaitPin()); // enter PIN state
        display.show("Card read. Please enter PIN.");
    }

    public void onPinEntered(String pin) {
        // route through PIN state machine each time
        var pinCtx = new PinContext(this);
        pinCtx.setState(new AwaitPin());
        pinCtx.handlePin(pin);
    }

    public void onAmountEntered(String amountStr) {
        if (stage != Stage.AWAIT_AMOUNT) { display.show("Amount not expected now."); return; }
        int amount;
        try { amount = Integer.parseInt(amountStr.trim()); }
        catch (Exception e){ display.show("Invalid amount"); return; }

        // local checks (ATM capabilities)
        if (amount % 100 != 0) { display.show("Use multiples of 100"); return; }
        if (!dispenser.canDispense(amount)) { display.show("ATM cannot dispense this amount"); return; }

        // remote policy via proxy
        AmountDecision dec = bank.precheck(currentCard.pan, amount);
        if (dec != AmountDecision.OK) { display.show("Declined: " + dec); return; }

        // build bundle via strategy
        Map<Integer,Integer> bundle = strategy.makeBundle(amount, ATMConfig.get().denominations);
        if (bundle.isEmpty()) { display.show("Cannot make exact note bundle"); return; }

        // finalize
        WithdrawalResult wr = bank.withdraw(currentCard.pan, amount);
        if (wr.status() == WithdrawalResult.Status.OK) {
            dispenser.dispense(bundle);
            printer.print(currentCard.masked(), amount, wr.balanceAfter());
            display.show("Collect cash. Balance: " + wr.balanceAfter());
            eject();
        } else {
            display.show("Withdrawal failed: " + wr.status());
            eject();
        }
    }

    public void eject() {
        reader.eject();
        currentCard = null;
        stage = Stage.IDLE;
        display.show("Thank you.");
    }
}
