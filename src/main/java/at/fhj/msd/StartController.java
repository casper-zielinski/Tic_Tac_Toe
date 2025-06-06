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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    private Pane Option_Pane;

    private Button playerVsPlayerButton;
    private Button playerVsComputerButton;

    public void Click_else_where(MouseEvent event) {
      if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
      {
            if (event.getTarget() != Option_Pane) {
          btn_exit.setStyle("-fx-background-color: #9b1690;");//Reset button style
          btn_start.setStyle("-fx-background-color: #9b1690;");
          btn_rules.setStyle("-fx-background-color: #9b1690;");
            } 
      }
      
    }

    @FXML
    void Exit(ActionEvent event) {
      btn_start.setStyle("-fx-background-color: #9b1690;");//Reset button style
      btn_rules.setStyle("-fx-background-color: #9b1690;");

      btn_exit.setStyle("-fx-background-color: #580b52");
    }

    public Optional<ButtonType> result; //Dialog result
    private Stage stage; //Stage for the GAME
    private Scene scene; //Scene for the GAME
    private Parent root; //Root for the GAME

    @FXML
    @SuppressWarnings("CallToPrintStackTrace")
    void Start_game(ActionEvent event) {
      btn_exit.setStyle("-fx-background-color: #9b1690;");//Reset button style
      btn_rules.setStyle("-fx-background-color: #9b1690;");

      btn_start.setStyle("-fx-background-color: #580b52");


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
            } catch (IOException e) {
              System.out.println("Error loading Player vs Player scene: " + e.getMessage());
              e.printStackTrace();
            } // Call the method to set the Player vs Player scene
      } 
      else if (result.isPresent() && result.get() == playerVsComputer)
      {
            // Handle Player vs Computer selection
            System.out.println("Player vs Computer selected");
            try {
                  setPlayerVsXScene(); 
            } catch (IOException e) {
                  System.out.println("Error loading Player vs Computer scene: " + e.getMessage());
                  e.printStackTrace();
            }
            // Load the player vs computer game scene here
            // For example, you can use FXMLLoader to load a new FXML file for the game
      } 
      else {
            System.out.println("Game selection cancelled");
      }

    }

    private void setPlayerVsXScene() throws IOException {
     root = FXMLLoader.load(getClass().getResource("/TicTacToe.fxml"));
     stage = (Stage) btn_start.getScene().getWindow(); // Get the current stage
     scene = new Scene(root);
     stage.setScene(scene);
     stage.setTitle("Tic Tac Toe Game");
     scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
    }

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
