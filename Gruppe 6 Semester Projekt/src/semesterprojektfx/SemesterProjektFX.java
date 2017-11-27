/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private String fxmlDocument = "FXMLDocument.fxml";
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(fxmlDocument));    
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
    public String getFXML(){
        return fxmlDocument;
    }
    public String setFXML(String newFXML){
        this.fxmlDocument = newFXML;
        return fxmlDocument;
    }
  }
