package CaseStudies.TicTacToeDemo;

public class NationalRules implements GameRules {
    @Override
    public boolean checkWin(Board board, char symbol) {
        // Standard win conditions
        if (board.checkWin(symbol)) {
            return true;
        }
        
        // National rule variant: Win if you have 3 corners (for a 3x3 board)
        int size = board.getSize();
        if (size == 3) {
            char[][] boardData = board.getBoardData();
            // Check if 3 corners are occupied by the same symbol
            if (boardData[0][0] == symbol && boardData[0][2] == symbol && boardData[2][0] == symbol) {
                return true;
            }
            if (boardData[0][0] == symbol && boardData[0][2] == symbol && boardData[2][2] == symbol) {
                return true;
            }
            if (boardData[0][0] == symbol && boardData[2][0] == symbol && boardData[2][2] == symbol) {
                return true;
            }
            if (boardData[0][2] == symbol && boardData[2][0] == symbol && boardData[2][2] == symbol) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isValidMove(Board board, int row, int col) {
        int size = board.getSize();
        return row >= 0 && row < size && col >= 0 && col < size;
    }
    
    @Override
    public String getRuleName() {
        return "National Rules";
    }
}
