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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author goope
 */
public class FXMLDocumentController implements Initializable {
    
    private TextOut textOut;
    private Game game;
    private Parser parser;
    private CommandWords commands;
    private Command command;
    
    private int flag = 0;
    
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
    private void initContextMenu() {
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem item1 = new MenuItem("Take");
        final MenuItem item2 = new MenuItem("Drop");
        
        contextMenu.getItems().addAll(item1, item2);
        mainMap.setOnContextMenuRequested(e -> contextMenu.show(mainMap, e.getScreenX(), e.getScreenY()));
    }
    
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
            textOutArea.clear();
            command.setSecondWord("north");
            toAppend = game.goRoom(command);
        }
        else if (event.getSource() == eastButton) {
            textOutArea.clear();
            command.setSecondWord("east");
            toAppend = game.goRoom(command);
        }
        else if (event.getSource() == westButton) {
            textOutArea.clear();
            command.setSecondWord("west");
            toAppend = game.goRoom(command);
        }
        else if (event.getSource() == southButton) {
            textOutArea.clear();
            command.setSecondWord("south");
            toAppend = game.goRoom(command);
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
        textOutArea.setText("You clicked!");
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
        parser = new Parser();
        commands = new CommandWords();
        command = parser.getCommand();
    } 
    
    
    //Reconsider this method later!!!! Function is to print the welcome text when
    //the mouse enters the application area.
    @FXML
    private void mouseEnter(MouseEvent event) {
        if (flag == 0) {
            textOutArea.appendText(game.printWelcome());
            textOutArea.appendText("\n");
            textOutArea.appendText("\n");            
            textOutArea.appendText(game.currentRoom.getLongDescription());
            flag = 1;
        }
    }

    @FXML
    private void mouseMenuOpen(ContextMenuEvent event) {
            
    }
}
