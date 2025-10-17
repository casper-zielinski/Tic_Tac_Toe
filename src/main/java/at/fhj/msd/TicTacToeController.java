package at.fhj.msd;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import at.fhj.msd.Logic.ComputerLogic;
import at.fhj.msd.Logic.Logic;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TicTacToeController implements Initializable {

    // Determines whether the game is Player vs Computer (true) or Player vs Player (false)
    private boolean playerVsComputer;

    /**
     * Sets the game mode for the current session.
     * Called by StartController when initializing the game scene.
     *
     * @param GameMode true for Player vs Computer mode, false for Player vs Player mode
     */
    public void setGameMode(boolean GameMode) {
        this.playerVsComputer = GameMode;
    }

    @FXML
    private Label Playing_text;

    // Label that displays which player's turn is next (X or O)
    @FXML
    private Label next_Player = new Label(getCurrentPlayer());

    // Maps grid positions (e.g., "0-0" for row 0, column 0) to their corresponding TextField UI elements
    private Map<String, TextField> textfields = new HashMap<>();

    /**
     * TextField UI elements representing the 3x3 Tic Tac Toe board.
     * Each field is clickable and displays either X, O, or remains empty.
     */
    @FXML
    private TextField bottom_left;

    @FXML
    private TextField bottom_middle;

    @FXML
    private TextField bottom_right;

    @FXML
    private TextField middle_left;

    @FXML
    private TextField middle_middle;

    @FXML
    private TextField middle_right;

    @FXML
    private TextField top_left;

    @FXML
    private TextField top_middle;

    @FXML
    private TextField top_right;

    /**
     * UI buttons for game control
     */
    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_exit;

    // Reference to the current JavaFX stage (window)
    private Stage stage;

    // 3x3 array representing the game board state. Each element contains "X", "O", or "" (empty)
    private String[][] Grid = {{"", "", ""},
    {"", "", ""},
    {"", "", ""}};

    /**
     * Terminates the application when the exit button is clicked.
     *
     * @param event The button click event
     */
    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
        stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }

    /**
     * Safely retrieves text from a TextField, returning an empty string if the TextField is null.
     * Prevents NullPointerException when accessing TextField content.
     *
     * @param tf The TextField to read from
     * @return The text content of the TextField, or an empty string if the TextField is null
     */
    private String safeText(TextField tf) {
        return tf != null ? tf.getText() : "";
    }

    /**
     * Clears all TextField elements on the game board by setting their text to empty strings.
     * Used when resetting the game or starting a new round.
     */
    private void ClearTextBox() {
        for (Map.Entry<String, TextField> entry : textfields.entrySet()) {
            entry.getValue().setText("");
        }
    }

    /**
     * Updates all TextField UI elements to match the current state of the Grid array.
     * Used primarily after the computer makes a move in Player vs Computer mode.
     * Parses the position key (e.g., "1-2") to extract row and column indices.
     */
    private void SetTextBox() {
        for (Map.Entry<String, TextField> entry : textfields.entrySet()) {
            int reihe = Character.getNumericValue(entry.getKey().charAt(0));
            int spalte = Character.getNumericValue(entry.getKey().charAt(2));

            entry.getValue().setText(this.Grid[reihe][spalte]);
        }
    }

    /**
     * Resets the game to its initial state by clearing both the UI TextFields and the internal Grid array.
     * Displays a confirmation dialog and calls Checker() to reinitialize the game flow.
     * Called when the reset button is clicked.
     *
     * @param event The button click event
     */
    @FXML
    @SuppressWarnings("CallToPrintStackTrace")
    void Reset_tb(ActionEvent event) {
        ClearTextBox();
        Logic.clearArray(this.Grid);
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reset");
            alert.setHeaderText(null);
            alert.setContentText("The Game has been reset!");
            alert.setGraphic(icon);

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());

            alert.showAndWait();
            try {
                Checker();
            } catch (InterruptedException e) {
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }
        });

    }

    /**
     * Constructs a 2D array representation of the current board state by reading values from all TextFields.
     * Returns a snapshot of the game board that can be passed to the Logic class for validation.
     *
     * @return A 3x3 String array representing the current state of the game board
     */
    private String[][] gridSetter() {
        String[][] Updated = {
            {safeText(top_left), safeText(top_middle), safeText(top_right)},
            {safeText(middle_left), safeText(middle_middle), safeText(middle_right)},
            {safeText(bottom_left), safeText(bottom_middle), safeText(bottom_right)}
        };

        return Updated;
    }

    /**
     * Counter that tracks the number of moves made. Used to determine which player's turn it is.
     * Even values (0, 2, 4...) indicate X's turn, odd values (1, 3, 5...) indicate O's turn.
     */
    private int x_or_y = 0;

    /**
     * Determines the current player based on the move counter.
     *
     * @return "O" if the move counter is even (X's turn), "X" if odd (O's turn)
     */
    private String getCurrentPlayer() {
        if (x_or_y % 2 == 0) {
            return "O";
        } else {
            return "X";
        }
    }

    /**
     * Updates the UI label to display the next player's turn (X or O).
     * Uses Platform.runLater to ensure thread-safe UI updates.
     *
     * @param value The player symbol to display ("X" or "O")
     */
    void next_Player_setter(String value) {
        Platform.runLater(() -> next_Player.setText(value));
    }

    /**
     * Handles a click on the top-left cell (Grid position [0][0]).
     * Only allows placement if the cell is empty, preventing overwriting existing moves.
     * Places X or O based on the current turn, updates the next player indicator, and checks for game end.
     *
     * @param event The mouse click event
     */
    @FXML
    void setX_or_O_1(MouseEvent event) {
        if (this.Grid[0][0].equals("")) {
            if (x_or_y % 2 == 0) {
                top_left.setText("X");
                this.Grid[0][0] = "X";
                next_Player_setter("O");
            } else {
                top_left.setText("O");
                this.Grid[0][0] = "O";
                next_Player_setter("X");
            }
            x_or_y++;
            try {
                Checker();
            } catch (InterruptedException e) {
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }
        }
    }

    /**
     * Handles a click on the top-middle cell (Grid position [0][1]).
     * Only allows placement if the cell is empty, preventing overwriting existing moves.
     * Places X or O based on the current turn, updates the next player indicator, and checks for game end.
     *
     * @param event The mouse click event
     */
    @FXML
    void setX_or_O_2(MouseEvent event) {
        if (this.Grid[0][1].equals("")) {
            if (x_or_y % 2 == 0) {
                top_middle.setText("X");
                this.Grid[0][1] = "X";
                next_Player_setter("O");
            } else {
                top_middle.setText("O");
                this.Grid[0][1] = "O";
                next_Player_setter("X");
            }
            x_or_y++;
            try {
                Checker();
            } catch (InterruptedException e) {
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }
        }
    }

    /**
     * Handles a click on the top-right cell (Grid position [0][2]).
     * Only allows placement if the cell is empty, preventing overwriting existing moves.
     * Places X or O based on the current turn, updates the next player indicator, and checks for game end.
     *
     * @param event The mouse click event
     */
    @FXML
    void setX_or_O_3(MouseEvent event) {
        if (this.Grid[0][2].equals("")) {
            if (x_or_y % 2 == 0) {
                top_right.setText("X");
                this.Grid[0][2] = "X";
                next_Player_setter("O");
            } else {
                top_right.setText("O");
                this.Grid[0][2] = "O";
                next_Player_setter("X");
            }
            x_or_y++;
            try {
                Checker();
            } catch (InterruptedException e) {
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }
        }
    }

    /**
     * Handles a click on the middle-left cell (Grid position [1][0]).
     * Only allows placement if the cell is empty, preventing overwriting existing moves.
     * Places X or O based on the current turn, updates the next player indicator, and checks for game end.
     *
     * @param event The mouse click event
     */
    @FXML
    void setX_or_O_4(MouseEvent event) {
        if (this.Grid[1][0].equals("")) {
            if (x_or_y % 2 == 0) {
                middle_left.setText("X");
                this.Grid[1][0] = "X";
                next_Player_setter("O");
            } else {
                middle_left.setText("O");
                this.Grid[1][0] = "O";
                next_Player_setter("X");
            }
            x_or_y++;
            try {
                Checker();
            } catch (InterruptedException e) {
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }
        }
    }

    /**
     * Handles a click on the center cell (Grid position [1][1]).
     * Only allows placement if the cell is empty, preventing overwriting existing moves.
     * Places X or O based on the current turn, updates the next player indicator, and checks for game end.
     *
     * @param event The mouse click event
     */
    @FXML
    void setX_or_O_5(MouseEvent event) {
        if (this.Grid[1][1].equals("")) {
            if (x_or_y % 2 == 0) {
                middle_middle.setText("X");
                this.Grid[1][1] = "X";
                next_Player_setter("O");
            } else {
                middle_middle.setText("O");
                this.Grid[1][1] = "O";
                next_Player_setter("X");
            }
            x_or_y++;
            try {
                Checker();
            } catch (InterruptedException e) {
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }
        }
    }

    /**
     * Handles a click on the middle-right cell (Grid position [1][2]).
     * Only allows placement if the cell is empty, preventing overwriting existing moves.
     * Places X or O based on the current turn, updates the next player indicator, and checks for game end.
     *
     * @param event The mouse click event
     */
    @FXML
    void setX_or_O_6(MouseEvent event) {
        if (this.Grid[1][2].equals("")) {
            if (x_or_y % 2 == 0) {
                middle_right.setText("X");
                this.Grid[1][2] = "X";
                next_Player_setter("O");
            } else {
                middle_right.setText("O");
                this.Grid[1][2] = "O";
                next_Player_setter("X");
            }
            x_or_y++;
            try {
                Checker();
            } catch (InterruptedException e) {
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }
        }
    }

    /**
     * Handles a click on the bottom-left cell (Grid position [2][0]).
     * Only allows placement if the cell is empty, preventing overwriting existing moves.
     * Places X or O based on the current turn, updates the next player indicator, and checks for game end.
     *
     * @param event The mouse click event
     */
    @FXML
    void setX_or_O_7(MouseEvent event) {
        if (this.Grid[2][0].equals("")) {
            if (x_or_y % 2 == 0) {
                bottom_left.setText("X");
                this.Grid[2][0] = "X";
                next_Player_setter("O");
            } else {
                bottom_left.setText("O");
                this.Grid[2][0] = "O";
                next_Player_setter("X");

            }
            x_or_y++;
            try {
                Checker();
            } catch (InterruptedException e) {
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }
        }
    }

    /**
     * Handles a click on the bottom-middle cell (Grid position [2][1]).
     * Only allows placement if the cell is empty, preventing overwriting existing moves.
     * Places X or O based on the current turn, updates the next player indicator, and checks for game end.
     *
     * @param event The mouse click event
     */
    @FXML
    void setX_or_O_8(MouseEvent event) {
        if (this.Grid[2][1].equals("")) {
            if (x_or_y % 2 == 0) {
                bottom_middle.setText("X");
                this.Grid[2][1] = "X";
                next_Player_setter("O");
            } else {
                bottom_middle.setText("O");
                this.Grid[2][1] = "O";
                next_Player_setter("X");
            }
            x_or_y++;
            try {
                Checker();
            } catch (InterruptedException e) {
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }
        }
    }

    /**
     * Handles a click on the bottom-right cell (Grid position [2][2]).
     * Only allows placement if the cell is empty, preventing overwriting existing moves.
     * Places X or O based on the current turn, updates the next player indicator, and checks for game end.
     *
     * @param event The mouse click event
     */
    @FXML
    void setX_or_O_9(MouseEvent event) {
        if (this.Grid[2][2].equals("")) {
            if (x_or_y % 2 == 0) {
                bottom_right.setText("X");
                this.Grid[2][2] = "X";
                next_Player_setter("O");
            } else {
                bottom_right.setText("O");
                this.Grid[2][2] = "O";
                next_Player_setter("X");
            }
            x_or_y++;
            try {
                Checker();
            } catch (InterruptedException e) {
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }
        }
    }

    /**
     * Manages the game flow after each move by checking for win/tie conditions and triggering computer moves if applicable.
     * Runs in a separate thread to avoid blocking the UI. In Player vs Computer mode, disables user input during the computer's turn,
     * triggers the computer's move, and re-enables user input afterward.
     *
     * @throws InterruptedException If the checking thread is interrupted
     */
    void Checker() throws InterruptedException {
        new Thread(() -> {
            try {
                Checking_winner();
                if (x_or_y % 2 == 1 && this.playerVsComputer) {
                    for (Map.Entry<String, TextField> entry : textfields.entrySet()) {
                        entry.getValue().setDisable(true);
                    }
                    ComputerLogic cLogic = new ComputerLogic();
                    cLogic.computerZug(this.Grid);
                    SetTextBox();
                    if (!StopForInteruption) {
                        Checking_winner();
                    }

                    for (Map.Entry<String, TextField> entry : textfields.entrySet()) {
                        entry.getValue().setDisable(false);
                    }
                    x_or_y++;
                }
            } catch (InterruptedException e) {
                // Thread was interrupted
            } catch (Exception e) {
                e.printStackTrace();
                Alert ErrorAlert = new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner. Try to exit and restart the game.");
                ErrorAlert.setTitle("An Error Occurred");
                ErrorAlert.setGraphic(icon);
                DialogPane dialogPaneError = ErrorAlert.getDialogPane();
                dialogPaneError.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                ErrorAlert.showAndWait();

                e.printStackTrace();
            }

        }).start();
    }

    // Flag to prevent game logic from running during dialog display or game reset
    private volatile boolean StopForInteruption = false;

    // Instance of the game logic class that handles win/tie/validation checks
    private volatile Logic logic = new Logic();

    // Tracks if the initial player label has been set to avoid redundant updates
    private volatile boolean label_text_start = true;

    // Icon displayed in dialog windows throughout the game
    private final ImageView icon = new ImageView(new Image(getClass().getResource("/TicTacToe.png").toExternalForm()));

    /**
     * Evaluates the current game state to determine if a player has won or if the game is tied.
     * Displays a confirmation dialog when the game ends, offering the option to play again.
     * This method is called after each move to check the board state. It runs in a separate thread to avoid blocking the UI.
     *
     * @throws InterruptedException If the thread is interrupted during execution
     */
    @FXML
    void Checking_winner() throws InterruptedException {

        try {

            if (label_text_start) {
                Platform.runLater(() -> next_Player_setter("X"));
            }
            label_text_start = false;
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            this.Grid = gridSetter();

            if (logic.Game_Won(this.Grid) && !logic.NotAllowedChars(this.Grid)) {
                Platform.runLater(() -> {

                    StopForInteruption = true;
                    printGrid(this.Grid);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(getCurrentPlayer() + " has won!!!");
                    alert.setHeaderText("Game Won");
                    alert.setContentText(getCurrentPlayer() + " !\nYou have won the Game, do you want to play again?");
                    alert.setGraphic(icon);

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                    dialogPane.getStyleClass().add("custom-alert");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        ClearTextBox();
                        Logic.clearArray(this.Grid);
                        StopForInteruption = false;

                        try {
                            Checker();
                        } catch (InterruptedException ex) {
                            System.err.println("InterruptedException occurred, could not call Checker");
                        }
                    }
                });

            } else if (logic.Game_Tied(this.Grid)) {
                Platform.runLater(() -> {

                    StopForInteruption = true;
                    printGrid(this.Grid);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Game TIED");
                    alert.setHeaderText(null);
                    alert.setContentText("There is a tie between both parties, do you want to paly again?");
                    alert.setGraphic(icon);

                    DialogPane dailogPane = alert.getDialogPane();
                    dailogPane.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                    dailogPane.getStyleClass().add("custom-alert");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        ClearTextBox();
                        Logic.clearArray(this.Grid);
                        StopForInteruption = false;

                        try {
                            Checker();
                        } catch (InterruptedException ex) {
                            System.err.println("InterruptedException occurred");
                            // ex.printStackTrace();
                        }
                    }

                });

            }

        } catch (RuntimeException ex) {
            System.err.println("RuntimeException occurred");
            // ex.printStackTrace();
        }

    }

    /**
     * Initializes the controller when the FXML file is loaded.
     * Populates the textfields map with references to all TextField elements, sets all TextFields to non-editable
     * (user interaction is through clicks, not typing), and initializes the player turn indicator.
     *
     * @param location The location used to resolve relative paths for the root object
     * @param resources The resources used to localize the root object
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textfields.put("0-0", top_left);
        textfields.put("0-1", top_middle);
        textfields.put("0-2", top_right);
        textfields.put("1-0", middle_left);
        textfields.put("1-1", middle_middle);
        textfields.put("1-2", middle_right);
        textfields.put("2-0", bottom_left);
        textfields.put("2-1", bottom_middle);
        textfields.put("2-2", bottom_right);
        next_Player_setter("X");

        for (Map.Entry<String, TextField> entry : textfields.entrySet()) {
            entry.getValue().setEditable(false);
        }
    }

    /**
     * Prints the current state of the game board to the console in a formatted grid layout.
     * Used for debugging and logging game state.
     *
     * @param grid The 3x3 game board array to display
     */
    public void printGrid(String[][] grid) {
        // Method intentionally left empty - console output removed
    }

}
