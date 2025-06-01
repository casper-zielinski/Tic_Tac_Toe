package at.fhj.msd;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import at.fhj.msd.Logic.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class TicTacToeController {

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

    private String safeText(TextField tf) {
    return tf != null ? tf.getText() : "";
    }

      private String[][] Grid = {
      { safeText(top_left), safeText(top_middle), safeText(top_right) },
      { safeText(middle_left), safeText(middle_middle), safeText(middle_right) },
      { safeText(bottom_left), safeText(bottom_middle), safeText(bottom_right) }
      };
                              
                              
      

      private String[][] UpdatedGrid()
      {
            String[][] Updated = {
            { safeText(top_left), safeText(top_middle), safeText(top_right) },
            { safeText(middle_left), safeText(middle_middle), safeText(middle_right) },
            { safeText(bottom_left), safeText(bottom_middle), safeText(bottom_right) }
            };

            return Updated;
      }
      

    @FXML
    void next_round(ActionEvent event) {

    }

    @FXML
    void Checking_winner(ActionEvent event) {
      Dialog<String> dialog = new Dialog<>();

      if (Logic.Game_Won(UpdatedGrid()) && !Logic.NotAllowedChars(UpdatedGrid()))
      {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("You have won!!!");
            alert.setHeaderText("Game Won");
            alert.setContentText("You have won the Game, do you want to play again?");
            alert.showAndWait();

      }
      else if (Logic.NotAllowedChars(UpdatedGrid()))
      {
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("False Characters");
            alert.setHeaderText(null);
            alert.setContentText("You play TicTacToe, only use \"X\" and \"O\" ");
            alert.showAndWait(); // blockiert bis Benutzer schließt
      }
      else {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game goes on");
            alert.setHeaderText(null);
            alert.setContentText("Nobody won, the Game goes on");
            alert.showAndWait();
      }
      
    }





}
