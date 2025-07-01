package at.fhj.msd;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class StartController implements Initializable {

    @FXML
    private Label TicTacToeStart_lbl;

    @FXML
    private ImageView TicTacToe_Img;

    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_rules;

    @FXML
    private Button btn_start;

    @FXML
    private FlowPane Option_Pane;


    /**
     * Exit the application when the exit button is clicked.
     * This method is called when the exit button is clicked.
     * @param event
     */
    @FXML
    void Exit(ActionEvent event) { 
      btn_start.setStyle("-fx-background-color: #9b1690;");//Reset button style
      btn_rules.setStyle("-fx-background-color: #9b1690;");

      btn_exit.setStyle("-fx-background-color: #580b52");
      // Close the application
      Stage stage = (Stage) btn_exit.getScene().getWindow();
      stage.close();
    }

    public Optional<ButtonType> result; //Dialog result
    private Stage stage; //Stage for the GAME
    private Scene scene; //Scene for the GAME
    private Parent root; //Root for the GAME

    /**
     * Start the game when the start button is clicked.
     * This method is called when the start button is clicked.
     * A dialog is shown to select the game mode (Player vs Player or Player vs Computer).
     * The selected game mode is then used to load the appropriate game scene.
     * The Start Scene is closed after the selection is made.
     * If the user cancels the dialog, a message is printed to the console and the application remains on the start screen
     * and the dialog is closed.
     * @param event
     */
    @FXML
    @SuppressWarnings("CallToPrintStackTrace")
    void Start_game(ActionEvent event) {
      btn_exit.setStyle("-fx-background-color: #9b1690;");//Reset button style
      btn_rules.setStyle("-fx-background-color: #9b1690;");

      btn_start.setStyle("-fx-background-color: #580b52");


      /**
       * Initialize the dialog for game selection.
       * The dialog allows the user to choose between Player vs Player and Player vs Computer modes.
       */
      ButtonType playerVsPlayer = new ButtonType("Player vs Player");
      ButtonType playerVsComputer = new ButtonType("Player vs Computer");



      // Load the game scene
      Dialog<ButtonType> dialog = new Dialog<>();
      dialog.setTitle("Tic Tac Toe Game");
      dialog.setHeaderText("Welcome to Tic Tac Toe!");
      dialog.setContentText("How do you want to play?\n 1. Player vs Player\n 2. Player vs Computer");
      dialog.getDialogPane().getButtonTypes().addAll(playerVsComputer,playerVsPlayer, ButtonType.CANCEL);	

      result = dialog.showAndWait();

      if (result.isPresent() && result.get() == playerVsPlayer)
      {
            // Handle Player vs Player selection
            System.out.println("Player vs Player selected");
            try {
              setPlayerVsXScene();
              stage = (Stage) btn_start.getScene().getWindow(); // Get the current stage
              stage.close(); // Close the current stage
            } catch (IOException e) {
              System.out.println("Error loading Player vs Player scene: " + e.getMessage());
              e.printStackTrace();
            } 
            catch (NullPointerException e) { // You need to handle this exception to avoid NullPointerException and many others, this is simply to keep the terminal clean
                  System.out.println("Closed first Scene");
            }
      } 
      else if (result.isPresent() && result.get() == playerVsComputer)
      {
            // Handle Player vs Computer selection
            System.out.println("Player vs Computer selected");
            try {
                  setPlayerVsXScene(); 
                  stage = (Stage) btn_start.getScene().getWindow(); // Get the current stage
                  stage.close(); // Close the current stage
            } catch (IOException e) {
                  System.out.println("Error loading Player vs Computer scene: " + e.getMessage());
                  e.printStackTrace();
            }
            catch (NullPointerException e) { // You need to handle this exception to avoid NullPointerException and many others, this is simply to keep the terminal clean
                  System.out.println("Closed first Scene");
            }
      } 
      else {
            System.out.println("Game selection cancelled"); // If the user cancels the dialog, print a message
      }

    }

    /**
     * Set the scene for Player vs X (either Player vs Player or Player vs Computer).
     * This method loads the TicTacToe.fxml file and sets the scene for the game.
     * @throws IOException if the FXML file cannot be loaded
     */
    private void setPlayerVsXScene() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/TicTacToe.fxml"));
        stage = (Stage) btn_start.getScene().getWindow(); // Get the current stage
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe Game");
        scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
    }

    /**
     * Show the rules of the game when the rules button is clicked.
     * This method is called when the rules button is clicked.
     * It changes the style of the buttons to indicate that the rules button is active.
     * @param event
     */
    @FXML
    void show_Rules(ActionEvent event) {
      btn_exit.setStyle("-fx-background-color: #9b1690;");//Reset button style
      btn_start.setStyle("-fx-background-color: #9b1690;");


      btn_rules.setStyle("-fx-background-color: #580b52");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      // Initialize the StartController
      btn_exit.setMinWidth(150);
      btn_start.setMinWidth(150);      
      btn_rules.setMinWidth(150);
    }

}
