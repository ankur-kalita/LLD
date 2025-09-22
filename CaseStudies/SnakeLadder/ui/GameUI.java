package CaseStudies.SnakeLadder.ui;

import CaseStudies.SnakeLadder.models.Board;
import CaseStudies.SnakeLadder.models.Player;

public interface GameUI {
    void initialize(Board board, java.util.List<Player> players);
    void updatePlayerPosition(Player player);
    void showGameStatus(String message);
    void showDiceRoll(Player player, int diceValue);
    void showGameOver(Player winner);
    boolean getPlayerRollDiceInput(Player player);
    void close();
}
