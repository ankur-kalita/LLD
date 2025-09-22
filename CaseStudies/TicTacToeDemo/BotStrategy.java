package CaseStudies.TicTacToeDemo;

public interface BotStrategy {
    Move calculateMove(Board board, char symbol);
    String getStrategyName();
}
