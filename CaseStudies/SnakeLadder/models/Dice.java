package CaseStudies.SnakeLadder.models;

import java.util.Random;

// Strategy Pattern for dice rolling behavior
public class Dice {
    private DiceRollingStrategy strategy;
    private int numFaces;
    
    public Dice(int numFaces) {
        this.numFaces = numFaces;
        this.strategy = new DefaultDiceStrategy();
    }
    
    public void setStrategy(DiceRollingStrategy strategy) {
        this.strategy = strategy;
    }
    
    public int roll() {
        return strategy.roll(numFaces);
    }
    
    // Strategy interface
    public interface DiceRollingStrategy {
        int roll(int numFaces);
    }
    
    // Default implementation using random
    public static class DefaultDiceStrategy implements DiceRollingStrategy {
        private Random random = new Random();
        
        @Override
        public int roll(int numFaces) {
            return random.nextInt(numFaces) + 1;
        }
    }
    
    // Could add other strategies like CrookedDiceStrategy, TestDiceStrategy, etc.
}
