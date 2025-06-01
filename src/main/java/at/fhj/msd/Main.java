package at.fhj.msd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{

      @Override
      public void start(Stage Stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/TicTacToe.fxml"));
            Scene scene = new Scene(root, Color.AQUAMARINE);
            scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
            Stage.setScene(scene);
            Stage.setTitle("Tic Tac Toe");
            Stage.show();
      }

      public static void main(String[] args) {
            launch(args);
      }

}
