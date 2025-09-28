package CaseStudies.ATM.pin;

public interface PinState {
    void onEnter(PinContext ctx);
    void onPin(PinContext ctx, String pin);
}