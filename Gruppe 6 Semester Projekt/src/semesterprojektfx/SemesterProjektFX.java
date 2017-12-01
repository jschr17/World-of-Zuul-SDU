package semesterprojektfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
/**
 *
 * @author goope
 */
public class SemesterProjektFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));    
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Launches the GUI 
        launch(args);
        //Closes the rest of the program, after you have exited the GUI.
        System.exit(0);
    }
  }
