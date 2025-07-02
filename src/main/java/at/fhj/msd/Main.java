package at.fhj.msd;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{



      @Override
      public void start(Stage Stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/Start.fxml"));
            Scene scene = new Scene(root, Color.AQUAMARINE);
            scene.getStylesheets().add(getClass().getResource("/style/start.css").toExternalForm());            
            Stage.setScene(scene);
            Stage.setTitle("Tic Tac Toe");
            Stage.setOnCloseRequest(e -> System.exit(0));
            Image icon = new Image(getClass().getResourceAsStream("/TicTacToe.png"));
            Stage.getIcons().add(icon); 
            Stage.show();
      }

      public static void main(String[] args) {
            launch(args);
      }


}
