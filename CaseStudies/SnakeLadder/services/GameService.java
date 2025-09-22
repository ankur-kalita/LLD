package CaseStudies.SnakeLadder.services;

import java.util.ArrayList;
import java.util.List;

import CaseStudies.SnakeLadder.models.Board;
import CaseStudies.SnakeLadder.models.BotPlayer;
import CaseStudies.SnakeLadder.models.Dice;
import CaseStudies.SnakeLadder.models.HumanPlayer;
import CaseStudies.SnakeLadder.models.Player;
import CaseStudies.SnakeLadder.ui.GameUI;

public class GameService {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private int currentPlayerIndex;
    private boolean gameOver;
    private GameUI ui;
    
    public GameService() {
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.gameOver = false;
    }
    
    public void setUI(GameUI ui) {
        this.ui = ui;
    }
    
    public void initializeBoard(int size, int numSnakes, int numLadders) {
        Board.resetInstance(); // Reset any previous instance
        this.board = Board.getInstance(size);
        this.board.addSnakes(numSnakes);
        this.board.addLadders(numLadders);
        this.dice = new Dice(6); // Standard 6-faced dice
    }
    
    public void addPlayer(Player player) {
        this.players.add(player);
    }
    
    public void startGame() {
        if (players.size() < 1) {
            System.out.println("Need at least 1 player to start the game");
            return;
        }
        
        if (ui == null) {
            System.out.println("No UI set for the game");
            return;
        }
        
        ui.initialize(board, players);
        
        while (!gameOver) {
            Player currentPlayer = players.get(currentPlayerIndex);
            
            // Check if player wants to roll
            if (ui.getPlayerRollDiceInput(currentPlayer)) {
                int diceValue = dice.roll();
                ui.showDiceRoll(currentPlayer, diceValue);
                
                // Move the player
                int newPosition = currentPlayer.getPosition() + diceValue;
                
                // Check if player wins
                if (newPosition >= board.getBoardSize()) {
                    currentPlayer.setPosition(board.getBoardSize());
                    ui.updatePlayerPosition(currentPlayer);
                    ui.showGameOver(currentPlayer);
                    gameOver = true;
                    break;
                }
                
                // Check for snakes and ladders
                int finalPosition = board.getNextPosition(newPosition);
                currentPlayer.setPosition(finalPosition);
                ui.updatePlayerPosition(currentPlayer);
                
                // Move to next player
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
        
        ui.close();
    }
    
    public HumanPlayer createHumanPlayer(String name) {
        return new HumanPlayer(name);
    }
    
    public BotPlayer createBotPlayer(String name) {
        return new BotPlayer(name);
    }
    
    public BotPlayer createBotPlayer(String name, int thinkingTime) {
        return new BotPlayer(name, thinkingTime);
    }
}
