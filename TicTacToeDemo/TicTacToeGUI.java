import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements Game {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private JPanel boardPanel;
    private JPanel infoPanel;
    
    public TicTacToeGUI(int size, String player1Name, String player2Name) {
        board = new Board(size);
        players = new Player[2];
        players[0] = new Player(player1Name, 'X');
        players[1] = new Player(player2Name, 'O');
        currentPlayerIndex = 0;
        
        // Set up the JFrame
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());
        
        // Create status panel
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel player1Label = new JLabel("Player 1: " + players[0].getName() + " (" + players[0].getSymbol() + ")");
        JLabel player2Label = new JLabel("Player 2: " + players[1].getName() + " (" + players[1].getSymbol() + ")");
        statusLabel = new JLabel(players[currentPlayerIndex].getName() + "'s turn (" + players[currentPlayerIndex].getSymbol() + ")");
        
        infoPanel.add(player1Label);
        infoPanel.add(player2Label);
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
        
        setLocationRelativeTo(null);
    }
    
    private void handleButtonClick(int row, int col) {
        Player currentPlayer = players[currentPlayerIndex];
        
        // Try to make a move
        if (board.makeMove(row, col, currentPlayer.getSymbol())) {
            // Update the button
            buttons[row][col].setText(String.valueOf(currentPlayer.getSymbol()));
            buttons[row][col].setEnabled(false);
            
            // Check for win
            if (board.checkWin(currentPlayer.getSymbol())) {
                statusLabel.setText(currentPlayer.getName() + " wins!");
                disableAllButtons();
            } 
            // Check for draw
            else if (board.isFull()) {
                statusLabel.setText("The game is a draw!");
            } 
            // Switch player
            else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
                statusLabel.setText(players[currentPlayerIndex].getName() + "'s turn (" + 
                                    players[currentPlayerIndex].getSymbol() + ")");
            }
        }
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
        currentPlayerIndex = 0;
        
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        
        statusLabel.setText(players[currentPlayerIndex].getName() + "'s turn (" + 
                            players[currentPlayerIndex].getSymbol() + ")");
    }
    
    @Override
    public void launch() {
        setVisible(true);
    }
}
