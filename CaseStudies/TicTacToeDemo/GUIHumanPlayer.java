package CaseStudies.TicTacToeDemo;

public class GUIHumanPlayer extends Player {
    public GUIHumanPlayer(char symbol, String name) {
        super(symbol, name);
    }
    
    @Override
    public Move makeMove(Board board) {
        // This will be handled by the GUI button clicks
        // This method should never be called directly
        throw new UnsupportedOperationException("GUI Human Player moves are handled by the GUI");
    }
}
