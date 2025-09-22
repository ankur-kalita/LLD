package CaseStudies.SnakeLadder.models;

public abstract class Player {
    private String name;
    private int position;
    
    public Player(String name) {
        this.name = name;
        this.position = 0; // Start position
    }
    
    public String getName() {
        return name;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    
    // Method to decide if the player wants to roll the dice
    // For human players, this will be handled by UI
    // For bot players, this will be automatic
    public abstract boolean wantsToRoll();
}
