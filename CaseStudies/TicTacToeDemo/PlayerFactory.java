package CaseStudies.TicTacToeDemo;

public class PlayerFactory {
    public static Player createHumanPlayer(char symbol, String name) {
        return new HumanPlayer(symbol, name);
    }
    
    public static Player createGUIHumanPlayer(char symbol, String name) {
        return new GUIHumanPlayer(symbol, name);
    }
    
    public static Player createBotPlayer(char symbol, String name, DifficultyLevel difficultyLevel) {
        BotStrategy strategy;
        switch (difficultyLevel) {
            case EASY:
                strategy = new EasyBotStrategy();
                break;
            case MEDIUM:
                strategy = new MediumBotStrategy();
                break;
            case HARD:
                strategy = new HardBotStrategy();
                break;
            default:
                strategy = new EasyBotStrategy();
        }
        return new BotPlayer(symbol, name, strategy);
    }
}
