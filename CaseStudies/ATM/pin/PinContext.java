package CaseStudies.ATM.pin;

import CaseStudies.ATM.*;


public final class PinContext {
    public final ATM atm;
    int attempts = 0;

    private PinState state;

    public PinContext(ATM atm){ this.atm = atm; }

    public void setState(PinState s){ this.state = s; s.onEnter(this); }
    public void handlePin(String pin){ state.onPin(this, pin); }

    public boolean maxedOut(){ return attempts >= ATMConfig.get().pinMaxAttempts; }
    public void bumpAttempt(){ attempts++; }
}

