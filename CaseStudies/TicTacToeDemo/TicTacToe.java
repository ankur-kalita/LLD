package CaseStudies.TicTacToeDemo;

import java.util.Scanner;

public class TicTacToe implements GameInterface {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameRules rules;
    private Scanner scanner;
    
    public TicTacToe() {
        scanner = new Scanner(System.in);
    }
    
    @Override
    public void initialize() {
        System.out.println("Welcome to Tic Tac Toe!");
        
        // Select board size
        System.out.print("Enter board size (e.g., 3 for 3x3): ");
        int size = scanner.nextInt();
        board = new Board(size);
        
        // Create default rules
        rules = RulesFactory.createRules(RuleType.INTERNATIONAL);
        
        // Setup players
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter name for Player 1: ");
        String player1Name = scanner.nextLine();
        player1 = PlayerFactory.createHumanPlayer('X', player1Name);
        
        System.out.print("Enter name for Player 2: ");
        String player2Name = scanner.nextLine();
        player2 = PlayerFactory.createHumanPlayer('O', player2Name);
        
        currentPlayer = player1;
    }
    
    @Override
    public void start() {
        System.out.println("\nGame started!");
        System.out.println("Player 1: " + player1.getName() + " (" + player1.getSymbol() + ")");
        System.out.println("Player 2: " + player2.getName() + " (" + player2.getSymbol() + ")");
        
        while (true) {
            board.displayBoard();
            System.out.println("\n" + currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            
            Move move = currentPlayer.makeMove(board);
            
            if (rules.checkWin(board, currentPlayer.getSymbol())) {
                board.displayBoard();
                System.out.println("\n" + currentPlayer.getName() + " wins!");
                break;
            }
            
            if (board.isFull()) {
                board.displayBoard();
                System.out.println("\nGame is a draw!");
                break;
            }
            
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }
}