package CaseStudies.TicTacToeDemo;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner scanner;
    
    public HumanPlayer(char symbol, String name) {
        super(symbol, name);
        scanner = new Scanner(System.in);
    }
    
    @Override
    public Move makeMove(Board board) {
        int row, col;
        do {
            System.out.print(name + "'s turn. Enter row (0-" + (board.getSize() - 1) + "): ");
            row = scanner.nextInt();
            System.out.print("Enter column (0-" + (board.getSize() - 1) + "): ");
            col = scanner.nextInt();
            
            if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
                System.out.println("Invalid position! Try again.");
                continue;
            }
            
            if (!board.makeMove(row, col, symbol)) {
                System.out.println("Cell already occupied! Try again.");
            } else {
                break;
            }
        } while (true);
        
        return new Move(row, col);
    }
}
