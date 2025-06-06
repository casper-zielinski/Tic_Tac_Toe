package at.fhj.msd;


import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TicTacToeController implements Initializable{

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
    private ChoiceBox<String> cb_mode;

    private String[] Mode = {"Player vs. Player", "Player vs Computer"};

    private String safeText(TextField tf) {
    return tf != null ? tf.getText() : "";
    }

      private String[][] Grid = {
      { safeText(top_left), safeText(top_middle), safeText(top_right) },
      { safeText(middle_left), safeText(middle_middle), safeText(middle_right) },
      { safeText(bottom_left), safeText(bottom_middle), safeText(bottom_right) }
      };
                              
                              
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

      
      @FXML
      void Reset_tb(ActionEvent event) {
            ClearTextBox();
            Logic.clearArray(Grid);
            Platform.runLater(() -> {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Reset");
                  alert.setHeaderText(null);
                  alert.setContentText("The Game has been reset!");
                  alert.showAndWait();
                  try {
                        Checking_winner(event);
                  } catch (InterruptedException e) {
                       new Alert(Alert.AlertType.ERROR, "An error occurred while checking the winner.").showAndWait();
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
      

    @FXML
    void next_round(ActionEvent event) {
      
    }

 
    private int x_or_y  = 0;

    private String getWinner()
    {
      if (x_or_y % 2 == 0)
      {
            return "O";
      }
      else{
            return "X";
      }
    }



    @FXML
    void setX_or_O_1(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            top_left.setText("X");
      }
      else{
            top_left.setText("O");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_2(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            top_middle.setText("X");
      }
      else{
            top_middle.setText("O");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_3(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            top_right.setText("X");
      }
      else{
            top_right.setText("O");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_4(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            middle_left.setText("X");
      }
      else{
            middle_left.setText("O");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_5(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            middle_middle.setText("X");
      }
      else{
            middle_middle.setText("O");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_6(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            middle_right.setText("X");
      }
      else{
            middle_right.setText("O");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_7(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            bottom_left.setText("X");
      }
      else{
            bottom_left.setText("O");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_8(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            bottom_middle.setText("X");
      }
      else{
            bottom_middle.setText("O");
      }
      x_or_y++;
    }

    @FXML
    void setX_or_O_9(MouseEvent event) {
      if (x_or_y % 2 == 0)
      {
            bottom_right.setText("X");
      }
      else{
            bottom_right.setText("O");
      }
      x_or_y++;
    }

    @FXML
    void Checking_winner(ActionEvent event) throws InterruptedException{

      new Thread(() -> {
            try {
                  // Check if the game is won or tied
                  // and display an alert accordingly
                  while (true) { 
                        if (Logic.Game_Won(UpdatedGrid()) && !Logic.NotAllowedChars(UpdatedGrid()))
                        {
                              Platform.runLater(() -> {
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle(getWinner() + " has won!!!");
                                    alert.setHeaderText("Game Won");
                                    alert.setContentText(getWinner() + " !\nYou have won the Game, do you want to play again?");
                                    Optional<ButtonType> result = alert.showAndWait();

                                    if (result.isPresent() && result.get() == ButtonType.OK)
                                    {
                                          ClearTextBox();
                                          Logic.clearArray(Grid);

                                          
                                          try {
                                                Thread.sleep(150);
                                          } catch (InterruptedException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                          }
                                          try {
                                                Checking_winner(event);
                                          } catch (InterruptedException ex) {
                                                System.err.println("InterruptedException occurred");
                                                // ex.printStackTrace();
                                          }
                                    }
                              });
                              break;
                        }      
                        else if (Logic.Game_Tied(UpdatedGrid())) 
                        {
                              Platform.runLater(() -> {
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Game TIED");
                                    alert.setHeaderText(null);
                                    alert.setContentText("There is a tie between both parties, do you want to paly again?");

                                    Optional<ButtonType> result = alert.showAndWait();
                              
                                    if (result.isPresent() && result.get() == ButtonType.OK)
                                    {
                                          ClearTextBox();
                                          Logic.clearArray(Grid);
                                          try {
                                                Thread.sleep(150);
                                          } catch (InterruptedException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                          }
                                          try {
                                                Checking_winner(event);
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
      }).start();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
      cb_mode.getItems().addAll(Mode);

      bottom_left.setEditable(false);
      bottom_middle.setEditable(false);
      bottom_right.setEditable(false);

      middle_left.setEditable(false);
      middle_middle.setEditable(false);
      middle_right.setEditable(false);

      top_left.setEditable(false);
      top_middle.setEditable(false);
      top_right.setEditable(false);
    }





}
