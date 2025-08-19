import java.util.Scanner;

public class TicTacToe implements Game {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private Scanner scanner;
    
    public TicTacToe(int size, String player1Name, String player2Name) {
        board = new Board(size);
        players = new Player[2];
        players[0] = new Player(player1Name, 'X');
        players[1] = new Player(player2Name, 'O');
        currentPlayerIndex = 0;
        scanner = new Scanner(System.in);
    }
    
    @Override
    public void launch() {
        boolean gameEnded = false;
        
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Player 1: " + players[0].getName() + " (" + players[0].getSymbol() + ")");
        System.out.println("Player 2: " + players[1].getName() + " (" + players[1].getSymbol() + ")");
        
        while (!gameEnded) {
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println("\nCurrent board:");
            board.displayBoard();
            
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            
            boolean validMove = false;
            while (!validMove) {
                try {
                    System.out.print("Enter row (0-" + (board.getSize()-1) + "): ");
                    int row = scanner.nextInt();
                    System.out.print("Enter column (0-" + (board.getSize()-1) + "): ");
                    int col = scanner.nextInt();
                    
                    validMove = board.makeMove(row, col, currentPlayer.getSymbol());
                    if (!validMove) {
                        System.out.println("Invalid move! Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter numbers.");
                    scanner.nextLine(); // Clear input buffer
                }
            }
            
            if (board.checkWin(currentPlayer.getSymbol())) {
                System.out.println("\nFinal board:");
                board.displayBoard();
                System.out.println(currentPlayer.getName() + " wins!");
                gameEnded = true;
            } else if (board.isFull()) {
                System.out.println("\nFinal board:");
                board.displayBoard();
                System.out.println("The game is a draw!");
                gameEnded = true;
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            }
        }
    }
}