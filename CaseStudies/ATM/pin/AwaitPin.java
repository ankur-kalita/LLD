package CaseStudies.ATM.pin;

import CaseStudies.ATM.*;
import CaseStudies.ATM.bank.PinDecision;


public final class AwaitPin implements PinState {
    @Override public void onEnter(PinContext ctx) {
        ctx.atm.display.show("Enter PIN (attempt " + (ctx.attempts+1) + "/" + ATMConfig.get().pinMaxAttempts + ")");
    }

    @Override public void onPin(PinContext ctx, String pin) {
        ctx.bumpAttempt();

        // Local check #1: 4–6 digits
        if (pin == null || !pin.matches("\\d{4,6}")) {
            ctx.atm.display.show("Invalid PIN format");
            afterFail(ctx); return;
        }
        // Local check #2: super-weak patterns (all same digit)
        if (pin.chars().distinct().count() <= 1) {
            ctx.atm.display.show("Weak PIN pattern");
            afterFail(ctx); return;
        }
        // Remote verification
        PinDecision d = ctx.atm.bank.verifyPin(ctx.atm.currentCard.pan, pin);
        switch (d) {
            case OK -> { ctx.atm.display.show("PIN OK. Enter amount."); ctx.atm.stage = ATM.Stage.AWAIT_AMOUNT; }
            case CARD_BLOCKED -> { ctx.atm.display.show("Card blocked. Contact bank."); ctx.atm.eject(); }
            default -> { ctx.atm.display.show("Wrong PIN"); afterFail(ctx); }
        }
    }

    private void afterFail(PinContext ctx){
        if (ctx.maxedOut()) { ctx.atm.display.show("Too many attempts"); ctx.atm.eject(); }
        else ctx.atm.display.show("Try again");
    }
}

