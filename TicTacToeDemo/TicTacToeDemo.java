import java.util.Scanner;

public class TicTacToeDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the size of the board: ");
        int size = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Enter name for player 1: ");
        String player1Name = scanner.nextLine();
        
        System.out.print("Enter name for player 2: ");
        String player2Name = scanner.nextLine();
        
        System.out.print("Choose interface (1 for Console, 2 for GUI): ");
        int interfaceChoice = scanner.nextInt();
        
        Game game;
        if (interfaceChoice == 2) {
            game = new TicTacToeGUI(size, player1Name, player2Name);
        } else {
            game = new TicTacToe(size, player1Name, player2Name);
        }
        
        game.launch();
        
        scanner.close();
    }
}