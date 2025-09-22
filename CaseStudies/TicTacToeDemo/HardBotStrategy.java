package CaseStudies.TicTacToeDemo;

import java.util.ArrayList;
import java.util.List;

public class HardBotStrategy implements BotStrategy {
    @Override
    public Move calculateMove(Board board, char symbol) {
        char[][] boardData = board.getBoardData();
        int size = board.getSize();
        
        // Find the best move
        int[] bestMove = minimax(boardData, size, symbol, symbol, 0);
        int row = bestMove[0];
        int col = bestMove[1];
        
        board.makeMove(row, col, symbol);
        return new Move(row, col);
    }
    
    private int[] minimax(char[][] board, int size, char playerSymbol, char currentPlayer, int depth) {
        char opponentSymbol = (playerSymbol == 'X') ? 'O' : 'X';
        
        // Find available moves
        List<int[]> availableMoves = getAvailableMoves(board, size);
        
        // Check for terminal states
        if (isWinning(board, size, playerSymbol)) {
            return new int[]{-1, -1, 10 - depth};
        } else if (isWinning(board, size, opponentSymbol)) {
            return new int[]{-1, -1, depth - 10};
        } else if (availableMoves.isEmpty()) {
            return new int[]{-1, -1, 0};
        }
        
        int[] bestMove = new int[3];
        bestMove[0] = -1;
        bestMove[1] = -1;
        
        if (currentPlayer == playerSymbol) {
            bestMove[2] = Integer.MIN_VALUE;
            for (int[] move : availableMoves) {
                board[move[0]][move[1]] = playerSymbol;
                int[] scoreMove = minimax(board, size, playerSymbol, opponentSymbol, depth + 1);
                board[move[0]][move[1]] = '-';
                
                if (scoreMove[2] > bestMove[2]) {
                    bestMove[0] = move[0];
                    bestMove[1] = move[1];
                    bestMove[2] = scoreMove[2];
                }
            }
        } else {
            bestMove[2] = Integer.MAX_VALUE;
            for (int[] move : availableMoves) {
                board[move[0]][move[1]] = opponentSymbol;
                int[] scoreMove = minimax(board, size, playerSymbol, playerSymbol, depth + 1);
                board[move[0]][move[1]] = '-';
                
                if (scoreMove[2] < bestMove[2]) {
                    bestMove[0] = move[0];
                    bestMove[1] = move[1];
                    bestMove[2] = scoreMove[2];
                }
            }
        }
        
        return bestMove;
    }
    
    private List<int[]> getAvailableMoves(char[][] board, int size) {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '-') {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }
    
    private boolean isWinning(char[][] board, int size, char symbol) {
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
        return "Hard";
    }
}
