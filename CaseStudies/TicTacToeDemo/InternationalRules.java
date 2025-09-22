package CaseStudies.TicTacToeDemo;

public class InternationalRules implements GameRules {
    @Override
    public boolean checkWin(Board board, char symbol) {
        // Standard rules - win by completing a row, column, or diagonal
        return board.checkWin(symbol);
    }
    
    @Override
    public boolean isValidMove(Board board, int row, int col) {
        int size = board.getSize();
        return row >= 0 && row < size && col >= 0 && col < size;
    }
    
    @Override
    public String getRuleName() {
        return "International Rules";
    }
}
