package CaseStudies.TicTacToeDemo;

public class BotPlayer extends Player {
    private BotStrategy strategy;
    
    public BotPlayer(char symbol, String name, BotStrategy strategy) {
        super(symbol, name);
        this.strategy = strategy;
    }
    
    @Override
    public Move makeMove(Board board) {
        System.out.println(name + " (" + strategy.getStrategyName() + ") is making a move...");
        return strategy.calculateMove(board, symbol);
    }
    
    public void setStrategy(BotStrategy strategy) {
        this.strategy = strategy;
    }
}
