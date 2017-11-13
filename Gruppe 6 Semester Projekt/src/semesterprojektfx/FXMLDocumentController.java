/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektfx;

import Zuul_Framework.*;
import Persistens.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author goope
 */
public class FXMLDocumentController implements Initializable {
    
    private TextOut textOut;
    private Game game;
//    private InputFileToHashmapTest HashmapFile;
//    private InputHashmap textInput;
    
    @FXML
    TextArea textOutArea;
    @FXML
    private Button northButton;
    @FXML
    private Button eastButton;
    @FXML
    private Button southButton;
    @FXML
    private Button westButton;
    @FXML
    private Button helpButton;
    @FXML
    private ImageView mainMap;
    @FXML
    private ProgressBar AirBar;
    @FXML
    private ProgressBar HPbar;
    @FXML
    private ImageView miniMap;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String toAppend = "";
        if (event.getSource() == northButton) {   
            toAppend = textOut.getNorthMessage();
            HPbar.setProgress(game.player.getHp());
            AirBar.setProgress(game.player.getAir());
        }
        else if (event.getSource() == eastButton) {
            toAppend = textOut.getEastMessage();
        }
        else if (event.getSource() == westButton) {
            toAppend = textOut.getWestMessage();
        }
        else if (event.getSource() == southButton) {
            toAppend = textOut.getSouthMessage();
        }
        else if (event.getSource() == helpButton){
            textOutArea.clear();
            toAppend = helpText();
        }
        toAppend += System.lineSeparator();
        textOutArea.appendText(toAppend);
    }
    
    @FXML
    private void mouseClickAction(MouseEvent event) {
    } 
    private String helpText(){
        return game.printHelp();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textOut = new TextOut();
        try {
            game = new Game();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
