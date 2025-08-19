public class Board {
    private char[][] board;
    private int size;
    
    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        initializeBoard();
    }
    
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
    }
    
    public boolean makeMove(int row, int col, char symbol) {
        if (row < 0 || row >= size || col < 0 || col >= size || board[row][col] != '-') {
            return false;
        }
        board[row][col] = symbol;
        return true;
    }
    
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkWin(char symbol) {
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
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board[j][i] != symbol) {
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
    
    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public int getSize() {
        return size;
    }
}