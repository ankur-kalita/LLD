package CaseStudies.TicTacToeDemo;

public interface GameRules {
    boolean checkWin(Board board, char symbol);
    boolean isValidMove(Board board, int row, int col);
    String getRuleName();
}
