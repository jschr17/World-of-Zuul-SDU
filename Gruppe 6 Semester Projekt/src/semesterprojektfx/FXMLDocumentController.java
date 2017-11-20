/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektfx;

import Zuul_Framework.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

/**
 *
 * @author goope
 */
public class FXMLDocumentController implements Initializable {
    
    private TextOut textOut;
    Game game;
    Player player;
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
    private Button searchButton;
    @FXML
    private Button inspectButton;
    @FXML
    ImageView mainMap;
    
    @FXML
    private ProgressBar AirBar;
    @FXML
    private ProgressBar HPbar;
    @FXML
    private ImageView miniMap;
    @FXML
    private ListView<String> roomInventory = new ListView<>();
    private ObservableList<String> roomInv = FXCollections.observableArrayList();
    @FXML
    private ListView<String> playerInventory = new ListView<>();
    private ObservableList<String> playerInv = FXCollections.observableArrayList();
    
    ListProperty<String> listProperty1 = new SimpleListProperty<>();
    ListProperty<String> listProperty2 = new SimpleListProperty<>();    
    
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
        else if (event.getSource() == inspectButton) {
            
        }
        toAppend += System.lineSeparator();
        textOutArea.appendText(toAppend);
    }
    @FXML
    private void listAction(ActionEvent event){
        if (event.getSource() == searchButton){
            roomInv.clear();
            for (Immovable i : game.currentRoom.getInteractList()){
                if (i.getItems() != null) {
                    roomInv.add(i.getItems().getName());   
                }
            }
            listProperty1.set(FXCollections.observableList(roomInv));
            roomInventory.itemsProperty().bind(listProperty1);    
            if (game.player.getInventory() != null) {
                for (Item i : game.player.getInventory()) {
                        playerInv.add(i.getName());
                }               
            }
            else System.out.println("Nothing here.");
            listProperty2.set(FXCollections.observableList(playerInv));
            playerInventory.itemsProperty().bind(listProperty2);
        }
        else if (event.getSource() == inspectButton) {
           String immovableName = roomInventory.getSelectionModel().getSelectedItem();
//           Immovable object = immovable.
        }
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
            game.createRooms();
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
}