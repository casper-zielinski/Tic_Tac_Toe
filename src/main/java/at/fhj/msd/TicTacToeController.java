package at.fhj.msd;


import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

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

public class TicTacToeController implements Initializable{

    @FXML
    private Label Playing_text;

    @FXML
    private Label X_O_Indicator;

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

    @FXML
    private Button btn_check;

    @FXML
    private Button btn_reset;

   @FXML
    private Button btn_exit;

    private Stage stage;

    /**
     * To Exit the application when the exit button is clicked.
     * @param event
     */
    @FXML
    void Exit(ActionEvent event) {
      check_isrunning = false;
      System.exit(0);
      stage = (Stage) btn_exit.getScene().getWindow();
      stage.close();
    }

    /**
     * This method is used to safely get the text from a TextField.
     * If the TextField is null, it returns an empty string.
     * @param tf The TextField from which to get the text.
     * @return The text from the TextField or an empty string if the TextField is null.
     */
    private String safeText(TextField tf) {
    return tf != null ? tf.getText() : "";
    }
                              
      /**
       * This method clears the text in all TextFields.
       * It sets the text of each TextField to an empty string.
       */
      private void ClearTextBox() {
            
            top_left.setText("");
            top_middle.setText("");
            top_right.setText("");

            middle_left.setText("");
            middle_middle.setText("");
            middle_right.setText("");

            bottom_left.setText("");
            bottom_middle.setText("");
            bottom_right.setText("");
      }

      /**
       * This method resets the game by clearing the TextFields and the grid.
       * It also displays an alert to inform the user that the game has been reset.
       * It is called when the reset button is clicked.
       */
      @FXML
      @SuppressWarnings("CallToPrintStackTrace")
      void Reset_tb(ActionEvent event) {
            ClearTextBox();
            Logic.clearArray(Grid);
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

      private String[][] UpdatedGrid()
      {
            String[][] Updated = {
            { safeText(top_left), safeText(top_middle), safeText(top_right) },
            { safeText(middle_left), safeText(middle_middle), safeText(middle_right) },
            { safeText(bottom_left), safeText(bottom_middle), safeText(bottom_right) }
            };

            return Updated;
      }
      



 /**
  * This variable keeps track of the current player/variable.
  */
    private int x_or_y  = 0;

    /**
     * This method returns the current player based on the value of x_or_y.
     * If x_or_y is even, it returns "O", otherwise it returns "X".
     * @return The current player as a String ("O" or "X").
     */
    private String getCurrentPlayer() 
    {
      if (x_or_y % 2 == 0)
      {
            return "O";
      }
      else{
            return "X";
      }
    }

    /*
     * to set the Label of the next Player (X or O)
     */
    void Indicator_Text_Setter(String value)
    {
      X_O_Indicator.setText(value);
    }

    /**
     * All those methods are used to set the text of the TextFields
     * based on the current player (X or O).
     */

   

    @FXML
    void setX_or_O_1(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            top_left.setText("X");
            Indicator_Text_Setter("O");
      }
      else{
            top_left.setText("O");
            Indicator_Text_Setter("X");
      }
      x_or_y++;
    }

    @FXML
    @SuppressWarnings("unused")
    void setX_or_O_2(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            top_middle.setText("X");
            Indicator_Text_Setter("O");
      }
      else{
            top_middle.setText("O");
            Indicator_Text_Setter("X");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_3(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            top_right.setText("X");
            Indicator_Text_Setter("O");
      }
      else{
            top_right.setText("O");
            Indicator_Text_Setter("X");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_4(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            middle_left.setText("X");
            Indicator_Text_Setter("O");
      }
      else{
            middle_left.setText("O");
            Indicator_Text_Setter("X");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_5(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            middle_middle.setText("X");
            Indicator_Text_Setter("O");
      }
      else{
            middle_middle.setText("O");
            Indicator_Text_Setter("X");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_6(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            middle_right.setText("X");
            Indicator_Text_Setter("O");
      }
      else{
            middle_right.setText("O");
            Indicator_Text_Setter("X");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_7(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            bottom_left.setText("X");
            Indicator_Text_Setter("O");
      }
      else{
            bottom_left.setText("O");
            Indicator_Text_Setter("X");

      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_8(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            bottom_middle.setText("X");
            Indicator_Text_Setter("O");
      }
      else{
            bottom_middle.setText("O");
            Indicator_Text_Setter("X");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_9(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            bottom_right.setText("X");
            Indicator_Text_Setter("O");
      }
      else{
            bottom_right.setText("O");
            Indicator_Text_Setter("X");
      }
      x_or_y++;
    }

    /**
     * Calling the Checking_winner Method and checking for Errors 
     */
    void Checker() throws InterruptedException{
      new Thread(() -> {
            try{
                 Checking_winner(); 
            }
            catch (InterruptedException e)
            {
                  System.out.println("Checker Method exception");
            }
            catch (Exception e)
            {
                  System.out.println("Checker Method Error");
                  e.printStackTrace();
            }
            
      }).start();
    }

    private volatile boolean check_isrunning = false;
    private volatile Logic logic = new Logic();
    private String[][] Grid;
    private volatile int log_Grid = 0;
    private volatile boolean label_text_start = true;
    private ImageView icon = new ImageView(new Image(getClass().getResource("/TicTacToe.png").toExternalForm()));
    
    /**
     * Note: This method is called to check if the game has been won or tied.
     * It runs in a separate thread to avoid blocking the UI.
     * Needs to be worked on to ensure it works correctly with the game logic.
     * @param event
     * @throws InterruptedException
     */
    @FXML
    void Checking_winner() throws InterruptedException{
      
            try {
                  check_isrunning = true;
                  log_Grid = 0;
                  if (label_text_start) Indicator_Text_Setter("X");
                  label_text_start = false;
                  icon.setFitHeight(48);
                  icon.setFitWidth(48);

                  
                  
                  // Check if the game is won or tied
                  // and display an alert accordingly
                  while (check_isrunning) {

                        Grid = UpdatedGrid();
                        if (log_Grid == 0) printGrid(Grid);

                        log_Grid++;

                        if (logic.Game_Won(Grid) && !logic.NotAllowedChars(Grid))
                        {
                              Platform.runLater(() -> {

                                    printGrid(Grid);

                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle(getCurrentPlayer() + " has won!!!");
                                    alert.setHeaderText("Game Won");
                                    alert.setContentText(getCurrentPlayer() + " !\nYou have won the Game, do you want to play again?");
                                    alert.setGraphic(icon);

                                    DialogPane dialogPane = alert.getDialogPane();
                                    dialogPane.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                                    dialogPane.getStyleClass().add("custom-alert");

                                    Optional<ButtonType> result = alert.showAndWait();

                                    if (result.isPresent() && result.get() == ButtonType.OK)
                                    {
                                          ClearTextBox();
                                          Logic.clearArray(Grid);
                                          check_isrunning = false;
                                          
                                          // Removed Thread.sleep(150); as it is not recommended in UI/game logic
                                          try {
                                                
                                                Checker();
                                          } catch (InterruptedException ex) {
                                                System.err.println("InterruptedException occurred");
                                                // ex.printStackTrace();
                                          }
                                    }
                              });
                              break;
                        }     

                        else if (logic.Game_Tied(Grid)) 
                        {
                              Platform.runLater(() -> {

                                    printGrid(Grid);
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Game TIED");
                                    alert.setHeaderText(null);
                                    alert.setContentText("There is a tie between both parties, do you want to paly again?");
                                    alert.setGraphic(icon);

                                    DialogPane dailogPane = alert.getDialogPane();
                                    dailogPane.getStylesheets().add(getClass().getResource("/style/dialog.css").toExternalForm());
                                    dailogPane.getStyleClass().add("custom-alert");

                                    Optional<ButtonType> result = alert.showAndWait();
                              
                                    if (result.isPresent() && result.get() == ButtonType.OK)
                                    {
                                          ClearTextBox();
                                          Logic.clearArray(Grid);
                                          check_isrunning = false;
                                          
                                          try {
                                                Checker();
                                          } catch (InterruptedException ex) {
                                                System.err.println("InterruptedException occurred");
                                                // ex.printStackTrace();
                                          }
                                    }
                              
                                    
                              });

                              break;
                                    
                        }

                  }

            } catch (RuntimeException ex) {
                  System.err.println("RuntimeException occurred");
                  // ex.printStackTrace();
            }
      
    }


    /**
     * This method initializes the controller.
     * It is called when the FXML file is loaded.
     * It sets the TextFields to be non-editable
     * to prevent users from manually changing the text.
     * The Text is set by clicking on the TextFields, so the user can only interact with the game by clicking on them.
     * There is no need for the user to edit the TextFields manually.
     * That's why we set them to be non-editable.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

      bottom_left.setEditable(false);
      bottom_middle.setEditable(false);
      bottom_right.setEditable(false);

      middle_left.setEditable(false);
      middle_middle.setEditable(false);
      middle_right.setEditable(false);

      top_left.setEditable(false);
      top_middle.setEditable(false);
      top_right.setEditable(false);

      try {
           Checker();
      } catch (InterruptedException e) {
            System.out.println("initialize Method error for calling Checker, Interrupted Exception");
            e.printStackTrace();
      }
    }

    public void printGrid(String[][] grid) {
      System.out.println("\nAktuelles Spielfeld:");
      for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                  String value = grid[i][j];
                  if (value == null || value.isEmpty()) {
                  System.out.print("   "); // Leeres Feld
                  } else {
                  System.out.print(" " + value + " ");
                  }

                  if (j < grid[i].length - 1) {
                  System.out.print("|");
                  }
            }
            System.out.println();
            if (i < grid.length - 1) {
                  System.out.println("---+---+---");
            }
      }
   }

}
