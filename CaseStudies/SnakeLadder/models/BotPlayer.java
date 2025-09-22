package CaseStudies.SnakeLadder.models;

public class BotPlayer extends Player {
    private int thinkingTimeMs;
    
    public BotPlayer(String name) {
        super(name);
        this.thinkingTimeMs = 1000; 
    }
    
    public BotPlayer(String name, int thinkingTimeMs) {
        super(name);
        this.thinkingTimeMs = thinkingTimeMs;
    }
    
    @Override
    public boolean wantsToRoll() {
        try {
            Thread.sleep(thinkingTimeMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return true;
    }
    
    public void setThinkingTime(int thinkingTimeMs) {
        this.thinkingTimeMs = thinkingTimeMs;
    }
}
