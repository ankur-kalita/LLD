package CaseStudies.TicTacToeDemo;

public abstract class Player {
    protected char symbol;
    protected String name;
    
    public Player(char symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }
    
    public abstract Move makeMove(Board board);
    
    public char getSymbol() {
        return symbol;
    }
    
    public String getName() {
        return name;
    }
}
