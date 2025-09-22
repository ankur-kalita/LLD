package CaseStudies.SnakeLadder.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.swing.*;

import CaseStudies.SnakeLadder.models.Board;
import CaseStudies.SnakeLadder.models.HumanPlayer;
import CaseStudies.SnakeLadder.models.Ladder;
import CaseStudies.SnakeLadder.models.Player;
import CaseStudies.SnakeLadder.models.Snake;

public class SwingUI implements GameUI {
    private JFrame frame;
    private JPanel boardPanel;
    private JTextArea gameLog;
    private JButton rollDiceButton;
    private Board board;
    private List<Player> players;
    private Map<Player, Color> playerColors;
    private CountDownLatch diceRollLatch;
    
    private static final Color[] PLAYER_COLOR_OPTIONS = {
        Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, 
        Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA,
        new Color(128, 0, 128), new Color(165, 42, 42)
    };
    
    @Override
    public void initialize(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.playerColors = new HashMap<>();
        
        // Assign colors to players
        for (int i = 0; i < players.size(); i++) {
            playerColors.put(players.get(i), PLAYER_COLOR_OPTIONS[i % PLAYER_COLOR_OPTIONS.length]);
        }
        
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }
    
    private void createAndShowGUI() {
        frame = new JFrame("Snake and Ladder Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        // Create board panel
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };
        boardPanel.setPreferredSize(new Dimension(500, 500));
        
        // Create game log
        gameLog = new JTextArea();
        gameLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLog);
        scrollPane.setPreferredSize(new Dimension(250, 500));
        
        // Create dice roll button
        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(e -> {
            if (diceRollLatch != null) {
                diceRollLatch.countDown();
            }
        });
        rollDiceButton.setEnabled(false);
        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.add(rollDiceButton, BorderLayout.SOUTH);
        
        contentPane.add(boardPanel, BorderLayout.CENTER);
        contentPane.add(rightPanel, BorderLayout.EAST);
        
        frame.setVisible(true);
        
        // Log game info
        logGameInfo();
    }
    
    private void drawBoard(Graphics g) {
        int size = board.getSize();
        int cellSize = Math.min(boardPanel.getWidth(), boardPanel.getHeight()) / size;
        
        // Draw grid
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int x = j * cellSize;
                int y = (size - i - 1) * cellSize; // Invert y-axis to have 1 at the bottom
                
                // Alternate cell colors
                if ((i + j) % 2 == 0) {
                    g.setColor(new Color(230, 230, 230));
                } else {
                    g.setColor(new Color(200, 200, 200));
                }
                g.fillRect(x, y, cellSize, cellSize);
                
                // Draw cell border
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
                
                // Draw cell number
                int cellNumber = calculateCellNumber(i, j, size);
                g.drawString(String.valueOf(cellNumber), x + 5, y + 15);
            }
        }
        
        // Draw snakes
        g.setColor(Color.RED);
        for (Snake snake : board.getSnakes()) {
            Point head = getCellCenter(snake.getHead(), size, cellSize);
            Point tail = getCellCenter(snake.getTail(), size, cellSize);
            drawSnake(g, head, tail);
        }
        
        // Draw ladders
        g.setColor(Color.GREEN);
        for (Ladder ladder : board.getLadders()) {
            Point start = getCellCenter(ladder.getStart(), size, cellSize);
            Point end = getCellCenter(ladder.getEnd(), size, cellSize);
            drawLadder(g, start, end);
        }
        
        // Draw players
        for (Player player : players) {
            Point position = getCellCenter(player.getPosition(), size, cellSize);
            g.setColor(playerColors.get(player));
            g.fillOval(position.x - 10, position.y - 10, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(player.getName().substring(0, 1), position.x - 3, position.y + 5);
        }
    }
    
    private int calculateCellNumber(int row, int col, int size) {
        // Calculate cell number based on snake and ladder game convention
        // Bottom row starts with 1, and numbers increase in a snake-like pattern
        int cellNum;
        if (row % 2 == 0) {
            // Even rows go left to right
            cellNum = row * size + col + 1;
        } else {
            // Odd rows go right to left
            cellNum = (row + 1) * size - col;
        }
        return cellNum;
    }
    
    private Point getCellCenter(int position, int size, int cellSize) {
        if (position <= 0) return new Point(cellSize/2, (size * cellSize) - cellSize/2);
        
        position--; // Convert to 0-indexed
        int row = position / size;
        int col;
        if (row % 2 == 0) {
            // Even rows go left to right
            col = position % size;
        } else {
            // Odd rows go right to left
            col = size - 1 - (position % size);
        }
        
        return new Point(
            col * cellSize + cellSize/2,
            (size - row - 1) * cellSize + cellSize/2
        );
    }
    
    private void drawSnake(Graphics g, Point head, Point tail) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(head.x, head.y, tail.x, tail.y);
        
        // Draw snake head
        g.fillOval(head.x - 8, head.y - 8, 16, 16);
    }
    
    private void drawLadder(Graphics g, Point start, Point end) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(start.x, start.y, end.x, end.y);
        
        // Draw ladder rungs
        double dx = end.x - start.x;
        double dy = end.y - start.y;
        double length = Math.sqrt(dx*dx + dy*dy);
        double rungs = length / 20; // A rung every 20 pixels
        
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < rungs; i++) {
            double t = i / rungs;
            int x1 = (int)(start.x + dx * t);
            int y1 = (int)(start.y + dy * t);
            
            // Calculate perpendicular line for rung
            double perpX = -dy / length * 10;
            double perpY = dx / length * 10;
            
            g.drawLine(
                (int)(x1 - perpX), 
                (int)(y1 - perpY), 
                (int)(x1 + perpX), 
                (int)(y1 + perpY)
            );
        }
    }
    
    @Override
    public void updatePlayerPosition(Player player) {
        SwingUtilities.invokeLater(() -> {
            boardPanel.repaint();
            gameLog.append(player.getName() + " moved to position " + player.getPosition() + "\n");
            scrollToBottom();
        });
    }
    
    @Override
    public void showGameStatus(String message) {
        SwingUtilities.invokeLater(() -> {
            gameLog.append(message + "\n");
            scrollToBottom();
        });
    }
    
    @Override
    public void showDiceRoll(Player player, int diceValue) {
        SwingUtilities.invokeLater(() -> {
            gameLog.append(player.getName() + " rolled a " + diceValue + "\n");
            scrollToBottom();
        });
    }
    
    @Override
    public void showGameOver(Player winner) {
        SwingUtilities.invokeLater(() -> {
            gameLog.append("\n🎉 " + winner.getName() + " wins the game! 🎉\n");
            scrollToBottom();
            
            JOptionPane.showMessageDialog(frame, 
                winner.getName() + " wins the game!", 
                "Game Over", 
                JOptionPane.INFORMATION_MESSAGE);
                
            rollDiceButton.setEnabled(false);
        });
    }
    
    @Override
    public boolean getPlayerRollDiceInput(Player player) {
        if (player instanceof HumanPlayer) {
            diceRollLatch = new CountDownLatch(1);
            
            SwingUtilities.invokeLater(() -> {
                gameLog.append("\n" + player.getName() + "'s turn. Click 'Roll Dice'...\n");
                scrollToBottom();
                rollDiceButton.setEnabled(true);
            });
            
            try {
                diceRollLatch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            SwingUtilities.invokeLater(() -> {
                rollDiceButton.setEnabled(false);
            });
        }
        return true;
    }
    
    @Override
    public void close() {
        SwingUtilities.invokeLater(() -> {
            frame.dispose();
        });
    }
    
    private void logGameInfo() {
        gameLog.append("Board Size: " + board.getSize() + "x" + board.getSize() + "\n");
        gameLog.append("Number of Snakes: " + board.getSnakes().size() + "\n");
        gameLog.append("Number of Ladders: " + board.getLadders().size() + "\n");
        gameLog.append("Players: " + players.size() + "\n\n");
        
        gameLog.append("Players:\n");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            String playerType = (player instanceof HumanPlayer) ? "Human" : "Bot";
            gameLog.append((i+1) + ". " + player.getName() + " (" + playerType + ")\n");
        }
        
        gameLog.append("\nGame starting...\n");
        scrollToBottom();
    }
    
    private void scrollToBottom() {
        gameLog.setCaretPosition(gameLog.getDocument().getLength());
    }
}
