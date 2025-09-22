package CaseStudies.SnakeLadder.ui;

import java.util.List;
import java.util.Scanner;

import CaseStudies.SnakeLadder.models.Board;
import CaseStudies.SnakeLadder.models.HumanPlayer;
import CaseStudies.SnakeLadder.models.Player;

public class ConsoleUI implements GameUI {
    private Scanner scanner;
    private Board board;
    
    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }
    
    @Override
    public void initialize(Board board, List<Player> players) {
        this.board = board;
        System.out.println("Starting Snake and Ladder game...");
        printGameInfo(board, players);
    }
    
    @Override
    public void updatePlayerPosition(Player player) {
        System.out.println(player.getName() + " moved to position " + player.getPosition());
    }
    
    @Override
    public void showGameStatus(String message) {
        System.out.println(message);
    }
    
    @Override
    public void showDiceRoll(Player player, int diceValue) {
        System.out.println(player.getName() + " rolled a " + diceValue);
    }
    
    @Override
    public void showGameOver(Player winner) {
        System.out.println("\n*******************************");
        System.out.println("🎉 " + winner.getName() + " wins the game! 🎉");
        System.out.println("*******************************\n");
    }
    
    @Override
    public boolean getPlayerRollDiceInput(Player player) {
        if (player instanceof HumanPlayer) {
            System.out.println("\n" + player.getName() + "'s turn. Press Enter to roll the dice...");
            scanner.nextLine();
        }
        return true;
    }
    
    @Override
    public void close() {
        scanner.close();
    }
    
    private void printGameInfo(Board board, List<Player> players) {
        System.out.println("Board Size: " + board.getSize() + "x" + board.getSize());
        System.out.println("Number of Snakes: " + board.getSnakes().size());
        System.out.println("Number of Ladders: " + board.getLadders().size());
        System.out.println("Players: " + players.size());
        
        System.out.println("\nSnakes:");
        board.getSnakes().forEach(snake -> 
            System.out.println("Head: " + snake.getHead() + ", Tail: " + snake.getTail()));
            
        System.out.println("\nLadders:");
        board.getLadders().forEach(ladder -> 
            System.out.println("Start: " + ladder.getStart() + ", End: " + ladder.getEnd()));
        
        System.out.println("\nPlayers:");
        int index = 1;
        for (Player player : players) {
            String playerType = (player instanceof HumanPlayer) ? "Human" : "Bot";
            System.out.println(index++ + ". " + player.getName() + " (" + playerType + ")");
        }
    }
}
