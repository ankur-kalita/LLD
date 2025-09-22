package CaseStudies.TicTacToeDemo;

import java.util.Random;

public class EasyBotStrategy implements BotStrategy {
    private Random random;
    
    public EasyBotStrategy() {
        random = new Random();
    }
    
    @Override
    public Move calculateMove(Board board, char symbol) {
        // Easy bot just makes random moves
        int size = board.getSize();
        int row, col;
        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (!board.makeMove(row, col, symbol));
        
        return new Move(row, col);
    }
    
    @Override
    public String getStrategyName() {
        return "Easy";
    }
}
