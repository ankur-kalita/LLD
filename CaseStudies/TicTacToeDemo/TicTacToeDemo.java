package CaseStudies.TicTacToeDemo;

import java.util.Scanner;

public class TicTacToeDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Tic-Tac-Toe Game!");
        System.out.print("Choose interface (1 for Console, 2 for GUI): ");
        int interfaceChoice = scanner.nextInt();
        
        GameInterface game;
        if (interfaceChoice == 2) {
            game = new TicTacToeGUI();
        } else {
            game = new TicTacToe();
        }
        
        game.initialize();
        game.start();
        
        scanner.close();
    }
}