package CaseStudies.TicTacToeDemo;

import java.util.Random;

public class MediumBotStrategy implements BotStrategy {
    private Random random;
    
    public MediumBotStrategy() {
        random = new Random();
    }
    
    @Override
    public Move calculateMove(Board board, char symbol) {
        char[][] boardData = board.getBoardData();
        int size = board.getSize();
        char opponentSymbol = (symbol == 'X') ? 'O' : 'X';
        
        // Try to win
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardData[i][j] == '-') {
                    // Try this move
                    boardData[i][j] = symbol;
                    // Check if it leads to a win
                    if (checkWin(boardData, size, symbol)) {
                        board.makeMove(i, j, symbol);
                        return new Move(i, j);
                    }
                    // Undo the move
                    boardData[i][j] = '-';
                }
            }
        }
        
        // Block opponent's win
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardData[i][j] == '-') {
                    // Try this move for opponent
                    boardData[i][j] = opponentSymbol;
                    // Check if opponent would win
                    if (checkWin(boardData, size, opponentSymbol)) {
                        board.makeMove(i, j, symbol);
                        return new Move(i, j);
                    }
                    // Undo the move
                    boardData[i][j] = '-';
                }
            }
        }
        
        // Otherwise make a random move
        int row, col;
        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (!board.makeMove(row, col, symbol));
        
        return new Move(row, col);
    }
    
    private boolean checkWin(char[][] board, int size, char symbol) {
        // Check rows
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board[i][j] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        
        // Check columns
        for (int j = 0; j < size; j++) {
            boolean win = true;
            for (int i = 0; i < size; i++) {
                if (board[i][j] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        
        // Check diagonal
        boolean win = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i] != symbol) {
                win = false;
                break;
            }
        }
        if (win) return true;
        
        // Check anti-diagonal
        win = true;
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i] != symbol) {
                win = false;
                break;
            }
        }
        return win;
    }
    
    @Override
    public String getStrategyName() {
        return "Medium";
    }
}
