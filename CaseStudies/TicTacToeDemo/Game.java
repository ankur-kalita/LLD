package CaseStudies.TicTacToeDemo;

import java.util.Scanner;

public class Game implements GameInterface {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameRules rules;
    private Scanner scanner;
    
    public Game() {
        scanner = new Scanner(System.in);
    }
    
    @Override
    public void initialize() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        
        // Select board size
        System.out.print("Enter board size (e.g., 3 for 3x3): ");
        int size = scanner.nextInt();
        board = new Board(size);
        
        // Select rules
        System.out.println("Select rules:");
        System.out.println("1. International Rules");
        System.out.println("2. National Rules");
        int ruleChoice = scanner.nextInt();
        RuleType ruleType = (ruleChoice == 2) ? RuleType.NATIONAL : RuleType.INTERNATIONAL;
        rules = RulesFactory.createRules(ruleType);
        
        // Setup players
        setupPlayers();
        
        currentPlayer = player1;
    }
    
    private void setupPlayers() {
        scanner.nextLine();  // Consume newline
        
        // Player 1 setup
        System.out.println("\nPlayer 1 (X):");
        System.out.println("1. Human");
        System.out.println("2. Bot");
        int player1Type = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        if (player1Type == 1) {
            System.out.print("Enter name for Player 1: ");
            String name = scanner.nextLine();
            player1 = PlayerFactory.createHumanPlayer('X', name);
        } else {
            System.out.print("Enter name for Bot Player 1: ");
            String name = scanner.nextLine();
            System.out.println("Select difficulty level for Bot Player 1:");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            int diffChoice = scanner.nextInt();
            DifficultyLevel diffLevel;
            switch (diffChoice) {
                case 1: diffLevel = DifficultyLevel.EASY; break;
                case 2: diffLevel = DifficultyLevel.MEDIUM; break;
                case 3: diffLevel = DifficultyLevel.HARD; break;
                default: diffLevel = DifficultyLevel.EASY;
            }
            player1 = PlayerFactory.createBotPlayer('X', name, diffLevel);
        }
        
        // Player 2 setup
        System.out.println("\nPlayer 2 (O):");
        System.out.println("1. Human");
        System.out.println("2. Bot");
        int player2Type = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        if (player2Type == 1) {
            System.out.print("Enter name for Player 2: ");
            String name = scanner.nextLine();
            player2 = PlayerFactory.createHumanPlayer('O', name);
        } else {
            System.out.print("Enter name for Bot Player 2: ");
            String name = scanner.nextLine();
            System.out.println("Select difficulty level for Bot Player 2:");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            int diffChoice = scanner.nextInt();
            DifficultyLevel diffLevel;
            switch (diffChoice) {
                case 1: diffLevel = DifficultyLevel.EASY; break;
                case 2: diffLevel = DifficultyLevel.MEDIUM; break;
                case 3: diffLevel = DifficultyLevel.HARD; break;
                default: diffLevel = DifficultyLevel.EASY;
            }
            player2 = PlayerFactory.createBotPlayer('O', name, diffLevel);
        }
    }
    
    @Override
    public void start() {
        System.out.println("\nGame started with " + rules.getRuleName());
        System.out.println("Board size: " + board.getSize() + "x" + board.getSize());
        
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
