# Tic Tac Toe

A JavaFX implementation of Tic Tac Toe with a graphical interface. The application supports both player versus player and player versus computer modes.

## Requirements

- Java 17 or higher
- Maven
- JavaFX 21

## Building and Running

Build the project:
```bash
mvn clean install
```

Run the application:
```bash
mvn javafx:run
```

or

```bash
mvn javafx:run -f "c:\msd_code\Tic_Tac_Toe\pom.xml"
```

## Project Structure

- `src/main/java/at/fhj/msd/Main.java` - Application entry point
- `src/main/java/at/fhj/msd/StartController.java` - Controller for the start screen
- `src/main/java/at/fhj/msd/TicTacToeController.java` - Controller for the game board and move handling
- `src/main/java/at/fhj/msd/Logic/Logic.java` - Game logic for win and tie detection
- `src/main/java/at/fhj/msd/Logic/ComputerLogic.java` - Computer player logic for single player mode
- `src/main/resources/Start.fxml` - Start screen layout
- `src/main/resources/TicTacToe.fxml` - Game board layout
- `src/main/resources/style/` - CSS stylesheets for UI styling
- `src/test/java/at/fhj/msd/LogicTest.java` - Unit tests for game logic

## Game Features

- Start screen with game mode selection
- Player vs Player mode
- Player vs Computer mode with computer opponent
- 3x3 game board with clickable cells
- Win detection for rows, columns, and diagonals
- Tie detection when all cells are filled
- Prevention of overwriting occupied cells
- Turn indicator showing which player is next
- Game reset button to start a new game
- Dialog prompts for game end with option to play again

## Computer Player Logic

In Player vs Computer mode, the computer plays as O and makes decisions in this order:

1. If the computer can win with the next move, it takes that move
2. If the player can win with their next move, the computer blocks it
3. If the center cell is empty, the computer takes it
4. The computer takes an available corner cell
5. The computer takes any remaining available cell

## Platform Configuration

The project is configured for Windows by default. To run on other platforms, edit `pom.xml` and change the `javafx.platform` property to `linux` or `mac`.
