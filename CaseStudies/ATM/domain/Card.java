package CaseStudies.ATM.domain;

public final class Card {
    public final String pan; // keep plain for demo
    public Card(String pan){ this.pan = pan; }
    public String masked(){ return pan.replaceAll(".(?=.{4})", "*"); }
}
