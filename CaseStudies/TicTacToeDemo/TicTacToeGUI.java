package CaseStudies.TicTacToeDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TicTacToeGUI extends JFrame implements GameInterface {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private JPanel boardPanel;
    private JPanel infoPanel;
    private GameRules rules;
    
    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout());
    }
    
    @Override
    public void initialize() {
        Scanner scanner = new Scanner(System.in);
        
        // Get board size
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
        scanner.nextLine(); // Consume newline
        
        // Player 1 setup
        System.out.println("\nPlayer 1 (X):");
        System.out.println("1. Human");
        System.out.println("2. Bot");
        int player1Type = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
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
        scanner.nextLine(); // Consume newline
        
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
        
        currentPlayer = player1;
        
        // Create GUI components
        setupGUI(size);
    }
    
    private void setupGUI(int size) {
        // Create status panel
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel player1Label = new JLabel("Player 1: " + player1.getName() + " (" + player1.getSymbol() + ")");
        JLabel player2Label = new JLabel("Player 2: " + player2.getName() + " (" + player2.getSymbol() + ")");
        JLabel rulesLabel = new JLabel("Rules: " + rules.getRuleName());
        statusLabel = new JLabel(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
        
        infoPanel.add(player1Label);
        infoPanel.add(player2Label);
        infoPanel.add(rulesLabel);
        infoPanel.add(new JSeparator());
        infoPanel.add(statusLabel);
        add(infoPanel, BorderLayout.NORTH);
        
        // Create board panel
        boardPanel = new JPanel(new GridLayout(size, size));
        buttons = new JButton[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(row, col);
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
        
        // Create restart button
        JButton restartButton = new JButton("Start New Game");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        add(restartButton, BorderLayout.SOUTH);
    }
    
    private void handleButtonClick(int row, int col) {
        if (board.makeMove(row, col, currentPlayer.getSymbol())) {
            // Update the button
            buttons[row][col].setText(String.valueOf(currentPlayer.getSymbol()));
            buttons[row][col].setEnabled(false);
            
            // Check for win
            if (rules.checkWin(board, currentPlayer.getSymbol())) {
                statusLabel.setText(currentPlayer.getName() + " wins!");
                disableAllButtons();
            } 
            // Check for draw
            else if (board.isFull()) {
                statusLabel.setText("The game is a draw!");
            } 
            // Switch player
            else {
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
                statusLabel.setText(currentPlayer.getName() + "'s turn (" + 
                                    currentPlayer.getSymbol() + ")");
                
                // If current player is a bot, make its move
                if (currentPlayer instanceof BotPlayer) {
                    makeBotMove();
                }
            }
        }
    }
    
    private void makeBotMove() {
        // Add a small delay to make the bot's move more visible
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Move move = currentPlayer.makeMove(board);
                int row = move.getRow();
                int col = move.getCol();
                
                // Update the button
                buttons[row][col].setText(String.valueOf(currentPlayer.getSymbol()));
                buttons[row][col].setEnabled(false);
                
                // Check for win
                if (rules.checkWin(board, currentPlayer.getSymbol())) {
                    statusLabel.setText(currentPlayer.getName() + " wins!");
                    disableAllButtons();
                } 
                // Check for draw
                else if (board.isFull()) {
                    statusLabel.setText("The game is a draw!");
                } 
                // Switch player
                else {
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                    statusLabel.setText(currentPlayer.getName() + "'s turn (" + 
                                        currentPlayer.getSymbol() + ")");
                    
                    // If new player is also a bot, make its move
                    if (currentPlayer instanceof BotPlayer) {
                        makeBotMove();
                    }
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void disableAllButtons() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    
    private void resetGame() {
        board = new Board(board.getSize());
        currentPlayer = player1;
        
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        
        statusLabel.setText(player1.getName() + "'s turn (" + player1.getSymbol() + ")");
        
        // If first player is a bot, make its move
        if (currentPlayer instanceof BotPlayer) {
            makeBotMove();
        }
    }
    
    @Override
    public void start() {
        setLocationRelativeTo(null);
        setVisible(true);
        
        // If first player is a bot, make its move
        if (currentPlayer instanceof BotPlayer) {
            makeBotMove();
        }
    }
}
