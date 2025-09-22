## Class Diagram

                       +-----------+
                       |    Game   |
                       +-----------+
                        /    |     \
                       /     |      \
                      /      |       \
            +--------+  +--------+  +----------------+
            |  Board |  | Player |  | WinningStrategy|
            +--------+  +--------+  +----------------+
                |          /  \            / | \
                v         /    \          /  |  \
            +------+  +------+ +------+ +---+ +---+ +-------+
            | Cell |  | Human| |  Bot | |Row| |Col| |Diagonal|
            +------+  +------+ +------+ +---+ +---+ +-------+
                |                                      
                v                                      
             +------+   +---------+   +-----------+
             | Move |   |CellState|   |PlayerType |
             +------+   +---------+   +-----------+
                            |              |
                            v              v
                       +----------+   +---------+
                       |GameStatus|   |PlayerFactory|
                       +----------+   +---------+



## Design Patterns Used

1. **Factory Pattern**:
   - Implemented in the `PlayerFactory` class to create different types of players (Human/Bot).
   - Benefits: Encapsulates player creation logic and hides implementation details.
   - Implementation: Factory methods that return appropriate Player subclass instances.

2. **Strategy Pattern**:
   - Used with the `WinningStrategy` interface and its various implementations.
   - Benefits: Allows different winning condition algorithms to be interchangeable.
   - Implementation: Multiple classes implementing the WinningStrategy interface.

3. **Builder Pattern**:
   - Implemented in the `Game` class for creating game instances with various configurations.
   - Benefits: Provides clear, step-by-step construction of complex Game objects.
   - Implementation: Builder class with fluent interface for game configuration.

4. **Singleton Pattern**:
   - Applied for the game controller to ensure only one game is running at a time.
   - Benefits: Centralized game state management and prevention of multiple concurrent games.
   - Implementation: Private constructor with static getInstance() method.

5. **Observer Pattern**:
   - Used to notify players when game state changes.
   - Benefits: Loose coupling between game state and UI/player notifications.
   - Implementation: Event listeners and notification mechanisms for game events.

## SOLID Principles Application

1. **Single Responsibility Principle (SRP)**:
   - Each class has a well-defined responsibility:
     - `Board`: Manages the grid and cell states
     - `Player`: Makes moves based on player type
     - `Game`: Orchestrates gameplay and rules
     - `WinningStrategy`: Determines win conditions

2. **Open/Closed Principle (OCP)**:
   - The system is open for extension but closed for modification:
     - New winning strategies can be added without modifying existing code
     - New player types can be added by extending the Player class
     - New game features can be added through composition

3. **Liskov Substitution Principle (LSP)**:
   - `HumanPlayer` and `BotPlayer` can be used interchangeably wherever a `Player` is expected.
   - Any winning strategy implementation can be substituted without affecting the game logic.

4. **Interface Segregation Principle (ISP)**:
   - Interfaces are specific and focused:
     - `WinningStrategy` includes only methods necessary for win detection
     - No client is forced to depend on methods it doesn't use

5. **Dependency Inversion Principle (DIP)**:
   - High-level modules depend on abstractions:
     - `Game` depends on the `Player` abstraction, not concrete player implementations
     - `Game` depends on `WinningStrategy` interface, not specific strategy implementations
     - `Board` operates on `Cell` abstractions

## Game Features

1. **Multiple Player Types**:
   - Human players with keyboard input
   - Bot players with configurable difficulty levels

2. **Customizable Game Elements**:
   - Players can choose their symbols (X or O)
   - Support for different board sizes
   - Multiple winning strategies can be combined

3. **Game Mechanics**:
   - Win detection (rows, columns, diagonals)
   - Draw detection
   - Invalid move handling
   - Current game state display

4. **Gameplay Controls**:
   - Move validation
   - Game state visualization after each move
   - Undo functionality for moves

## How to Run

Compile and run the `Main.java` file to start the game:

```bash
javac /Users/akquasar/Documents/Codes/LLD/CaseStudies/TicTacToeDemo/src/Main.java
java /Users/akquasar/Documents/Codes/LLD/CaseStudies/TicTacToeDemo/src.Main