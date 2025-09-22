package CaseStudies.SnakeLadder;

import java.util.Scanner;

import CaseStudies.SnakeLadder.models.Player;
import CaseStudies.SnakeLadder.services.GameService;
import CaseStudies.SnakeLadder.ui.ConsoleUI;
import CaseStudies.SnakeLadder.ui.GameUI;
import CaseStudies.SnakeLadder.ui.SwingUI;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameService gameService = new GameService();
        
        // Ask for UI preference
        System.out.println("Choose UI type:");
        System.out.println("1. Console");
        System.out.println("2. GUI");
        int uiChoice = getIntInput(scanner, 1, 2);
        
        GameUI ui;
        if (uiChoice == 1) {
            ui = new ConsoleUI();
        } else {
            ui = new SwingUI();
        }
        gameService.setUI(ui);
        
        // Ask for board configuration
        System.out.println("\nEnter board size (6-15):");
        int boardSize = getIntInput(scanner, 6, 15);
        
        System.out.println("Enter number of snakes (1-10):");
        int numSnakes = getIntInput(scanner, 1, 10);
        
        System.out.println("Enter number of ladders (1-10):");
        int numLadders = getIntInput(scanner, 1, 10);
        
        // Initialize the board
        gameService.initializeBoard(boardSize, numSnakes, numLadders);
        
        // Ask for number of players
        System.out.println("\nEnter number of players (1-10):");
        int numPlayers = getIntInput(scanner, 1, 10);
        
        // Set up each player
        for (int i = 1; i <= numPlayers; i++) {
            System.out.println("\nPlayer " + i + ":");
            System.out.println("1. Human Player");
            System.out.println("2. Bot Player");
            int playerType = getIntInput(scanner, 1, 2);
            
            System.out.println("Enter player name:");
            String playerName = scanner.nextLine();
            
            Player player;
            if (playerType == 1) {
                player = gameService.createHumanPlayer(playerName);
                System.out.println("Human player '" + playerName + "' added.");
            } else {
                System.out.println("Enter bot thinking time in milliseconds (500-3000):");
                int thinkingTime = getIntInput(scanner, 500, 3000);
                player = gameService.createBotPlayer(playerName, thinkingTime);
                System.out.println("Bot player '" + playerName + "' added with thinking time " + thinkingTime + "ms.");
            }
            
            gameService.addPlayer(player);
        }
        
        // If using console UI, close the scanner to avoid resource leak in UI
        if (uiChoice == 1) {
            scanner.close();
        }
        
        // Start the game
        gameService.startGame();
    }
    
    private static int getIntInput(Scanner scanner, int min, int max) {
        int input = -1;
        do {
            try {
                String line = scanner.nextLine();
                input = Integer.parseInt(line);
                if (input < min || input > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ":");
                    input = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number:");
            }
        } while (input < 0);
        return input;
    }
}
