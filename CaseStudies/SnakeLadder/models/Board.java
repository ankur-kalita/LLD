package CaseStudies.SnakeLadder.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board {
    private static Board instance;
    private Cell[][] cells;
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    
    private Board(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        initializeCells();
    }
    
    public static Board getInstance(int size) {
        if (instance == null) {
            instance = new Board(size);
        }
        return instance;
    }
    
    public static void resetInstance() {
        instance = null;
    }
    
    private void initializeCells() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }
    
    public void addSnakes(int numSnakes) {
        Random random = new Random();
        int maxPosition = size * size - 1;
        Map<Integer, Integer> occupiedPositions = new HashMap<>();
        
        for (int i = 0; i < numSnakes; i++) {
            int head = random.nextInt(maxPosition - 1) + 2; // Start from 2 to avoid first position
            int tail = random.nextInt(head - 1) + 1; // Tail should be less than head
            
            // Ensure we don't overlap with existing snakes or ladders
            if (!occupiedPositions.containsKey(head) && !occupiedPositions.containsKey(tail)) {
                Snake snake = new Snake(head, tail);
                snakes.add(snake);
                occupiedPositions.put(head, tail);
                occupiedPositions.put(tail, head);
            } else {
                i--; // Try again
            }
        }
    }
    
    public void addLadders(int numLadders) {
        Random random = new Random();
        int maxPosition = size * size - 1;
        Map<Integer, Integer> occupiedPositions = new HashMap<>();
        
        // Add existing snakes to occupied positions
        for (Snake snake : snakes) {
            occupiedPositions.put(snake.getHead(), snake.getTail());
            occupiedPositions.put(snake.getTail(), snake.getHead());
        }
        
        for (int i = 0; i < numLadders; i++) {
            int start = random.nextInt(maxPosition - 2) + 1; // Start from 1 to avoid last position
            int end = random.nextInt(maxPosition - start) + start + 1; // End should be greater than start
            
            // Ensure we don't overlap with existing snakes or ladders
            if (!occupiedPositions.containsKey(start) && !occupiedPositions.containsKey(end)) {
                Ladder ladder = new Ladder(start, end);
                ladders.add(ladder);
                occupiedPositions.put(start, end);
                occupiedPositions.put(end, start);
            } else {
                i--; // Try again
            }
        }
    }
    
    public int getSize() {
        return size;
    }
    
    public int getNextPosition(int position) {
        // Check if the position has a snake
        for (Snake snake : snakes) {
            if (snake.getHead() == position) {
                System.out.println("Oops! Snake bite. Going down from " + position + " to " + snake.getTail());
                return snake.getTail();
            }
        }
        
        // Check if the position has a ladder
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == position) {
                System.out.println("Yay! Climbing the ladder from " + position + " to " + ladder.getEnd());
                return ladder.getEnd();
            }
        }
        
        return position;
    }
    
    public int getBoardSize() {
        return size * size;
    }
    
    public List<Snake> getSnakes() {
        return snakes;
    }
    
    public List<Ladder> getLadders() {
        return ladders;
    }
}
