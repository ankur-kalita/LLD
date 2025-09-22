package CaseStudies.SnakeLadder.models;

public class HumanPlayer extends Player {
    
    public HumanPlayer(String name) {
        super(name);
    }
    
    @Override
    public boolean wantsToRoll() {
        // For human players, this is determined by UI input
        // The actual implementation will be in the UI classes
        return false;
    }
}
