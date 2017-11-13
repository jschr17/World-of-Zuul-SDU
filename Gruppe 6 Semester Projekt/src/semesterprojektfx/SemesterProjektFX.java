/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektfx;

import Zuul_Framework.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        launch(args);
//        Game game;
//        try {       // we are trying to catch the ioexeption trown from InputHasmap from persistens.
//            game = new Game();
//            game.play();
//        } catch (IOException e) {       // this is the catch where if an error from reading from the file shuld occur we want an errormessage printed
//            System.err.println("Caught IOException: " + e.getMessage());    // the printline for the error messeage
        //launch(args);
    }    
  }
//}
