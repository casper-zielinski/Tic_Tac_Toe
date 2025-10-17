# Tic Tac Toe

A JavaFX implementation of the Tic Tac Toe game with a graphical user interface.

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

## Project Structure

- `src/main/java/at/fhj/msd/Main.java` - Application entry point
- `src/main/java/at/fhj/msd/StartController.java` - Controller for the start screen
- `src/main/java/at/fhj/msd/TicTacToeController.java` - Controller for the game board
- `src/main/java/at/fhj/msd/Logic/Logic.java` - Game logic implementation
- `src/main/resources/Start.fxml` - Start screen layout
- `src/main/resources/TicTacToe.fxml` - Game board layout
- `src/test/java/at/fhj/msd/LogicTest.java` - Tests for game logic

## Game Features

- Start screen with game mode selection dialog
- Player vs Player mode
- 3x3 game board with clickable cells
- Win detection for rows, columns, and diagonals
- Tie detection
- Game reset functionality
- Visual feedback for current player turn

## Platform Configuration

The project is configured for Windows by default. To run on other platforms, edit `pom.xml` and change the `javafx.platform` property to `linux` or `mac`.
