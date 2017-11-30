/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektfx;

import Logic.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static java.util.Optional.empty;
import java.util.logging.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author goope
 */
public class FXMLDocumentController implements Initializable {
    
    Game game;
    Player player;
    private Parser parser;
    public Command command;
    private CommandWord commandWord;
    
    private int flag = 0;
    
    SemesterProjektFX starter = new SemesterProjektFX();
    Stage stage;
    
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
    private Button takeButton;
    @FXML
    private Button dropButton;
    @FXML
    private ImageView medkit;
    @FXML
    private ImageView oxygen;
    @FXML
    private ImageView mainMap1;
    @FXML
    private ImageView playerImg1;
    @FXML
    private ImageView monster;
    @FXML
    private ImageView eastDoor;
    @FXML
    private ImageView northDoor1;
    @FXML
    private ImageView southDoor;
    @FXML
    private Pane medbay;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView playerImg;
    @FXML
    private ImageView tableImg;
    @FXML
    private ImageView northDoor;
    @FXML
    private Pane keyRoom;
    @FXML
    private Pane armory;
    @FXML
    private ImageView secretDoor;
    @FXML
    private ImageView breakableTable;
    @FXML
    private ImageView rifleImg;
    @FXML
    private ImageView noteImg;
    @FXML
    private Pane hallway;
    @FXML
    private Pane airlock;
    @FXML
    private Pane communicationRoom;
    @FXML
    private Button attackButton;
    @FXML
    private Button useButton;
    @FXML
    private Button statusButton;
    
    //This method controlls the functions of the player movement buttons, and the
    //help button.
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        String toAppend = "";
        roomInventory.getItems().clear(); 
        if (event.getSource() == northButton) {
            textOutArea.clear();
            command.setSecondWord("north");          
            toAppend = game.goRoom(command);
            roomChange();
        }
        else if (event.getSource() == eastButton) {
            textOutArea.clear();
            command.setSecondWord("east");
            toAppend = game.goRoom(command);
            roomChange();
        }
        else if (event.getSource() == westButton) {
            textOutArea.clear();
            command.setSecondWord("west");
            toAppend = game.goRoom(command);
            roomChange();
        }
        else if (event.getSource() == southButton) {
            textOutArea.clear();
            command.setSecondWord("south");
            toAppend = game.goRoom(command);
            roomChange();
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
    
    //This method is used by the search funktion in the game. It gets the items
    //in the room the player is currently in, and adds their names to the GUI's
    //listviews.
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
            for (Item i : game.currentRoom.getItemList()){
                if (i != null) {
                    roomInv.add(i.getName());
                }
            }
        }
        else if (event.getSource() == inspectButton) {
           String immovableName = roomInventory.getSelectionModel().getSelectedItem();
        }
    }
    
    //This method controlls the take buttons actions, on how to deal with the items
    //in the room and player inventory.
    @FXML
    private void takeInventoryAction(ActionEvent event){
        String itemName = roomInventory.getSelectionModel().getSelectedItem();
        if (event.getSource() == takeButton) {
                if (itemName == null) {
                    return;
                }
                else if (playerInv.size() < game.inventorySpace) {
                    playerInv.add(itemName);
                    command.setSecondWord(itemName);
                    game.addInventory(command);
                    roomInventory.getItems().remove(itemName);
                    textOutArea.appendText("\nYou have added " + itemName + " to your inventory.");
                    if (itemName.equalsIgnoreCase(medkit.getId())) {
                        medkit.setVisible(false);
                    }
                    else if (itemName.equalsIgnoreCase(oxygen.getId())) {
                        oxygen.setVisible(false);
                    }
                }
                else if (playerInv.size() >= game.inventorySpace) {
                    textOutArea.appendText("\nNo more space in your inventoy.");
                }
            }
            listProperty2.set(FXCollections.observableList(playerInv));
            playerInventory.itemsProperty().bind(listProperty2);
        }
    
    //A test method for the functionality of the main viewport
    @FXML
    private void mouseClickAction(MouseEvent event) {
        textOutArea.setText("You clicked me, how naughty!");
    } 
    
    //Gets the help text string from the game class, so it can be used by the GUI
    private String helpText(){
        return game.printHelp();
    }
    
    //This method initializes all the relevant classes that are needed by the GUI
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            game = new Game();
            game.createRooms();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        parser = new Parser();
        command = parser.getCommand(); 
        
        textOutArea.appendText("\n");
        textOutArea.appendText(game.printWelcome());
        textOutArea.appendText("\n");
        textOutArea.appendText("\n");                      
        flag = 1;
        
        hpBarAction();
        AirBarAction();
    } 

    @FXML
    private void dropButtonAction(ActionEvent event) {        
        String itemName = playerInventory.getSelectionModel().getSelectedItem();
        command.setSecondWord(itemName);
        game.removeFromInventory(command);
        playerInventory.getItems().remove(itemName);
        roomInv.add(itemName);
        listProperty1.set(FXCollections.observableList(roomInv));
        roomInventory.itemsProperty().bind(listProperty1);  
        
        if (itemName.equalsIgnoreCase(medkit.getId())) {
            medkit.setVisible(true);
        }
        else if (itemName.equalsIgnoreCase(oxygen.getId())){
            oxygen.setVisible(true);
        }
    }
    
    private void roomChange() throws IOException{
        String roomName = game.currentRoom.getName();
        if (roomName.equalsIgnoreCase("medbay")) {
            armory.setVisible(false);
            keyRoom.setVisible(false);
            medbay.setVisible(true);
            hallway.setVisible(false);
            airlock.setVisible(false);
        }
        else if (roomName.equalsIgnoreCase("keyRoom")) {
            armory.setVisible(false);
            keyRoom.setVisible(true);
            medbay.setVisible(false);
            hallway.setVisible(false);
            airlock.setVisible(false);            
        }
        else if (roomName.equalsIgnoreCase("armoury")) {
            armory.setVisible(true);
            keyRoom.setVisible(false);
            medbay.setVisible(false);
            hallway.setVisible(false);
            airlock.setVisible(false);            
        }
        else if (roomName.equalsIgnoreCase("hallway")) {
            armory.setVisible(false);
            keyRoom.setVisible(false);
            medbay.setVisible(false); 
            hallway.setVisible(true);
            airlock.setVisible(false);
        }
        else if (roomName.equalsIgnoreCase("airlock")) {
            armory.setVisible(false);
            keyRoom.setVisible(false);
            medbay.setVisible(false); 
            hallway.setVisible(false);
            airlock.setVisible(true);
        }
        else if (roomName.equalsIgnoreCase("communicationRoom")) {
            armory.setVisible(false);
            keyRoom.setVisible(false);
            medbay.setVisible(false); 
            hallway.setVisible(false);
            airlock.setVisible(false);  
            communicationRoom.setVisible(true);
        }
    }
    
    @FXML
    private void useAction(ActionEvent event){
        String newWord = playerInventory.getSelectionModel().getSelectedItem();
        if (!playerInv.isEmpty() && newWord != "rifle") {
            command.setSecondWord(newWord);
            textOutArea.appendText("\n" + game.useItem(command));
            game.useItem(command);  
            playerInventory.getItems().remove(newWord);
        }
        else {
            textOutArea.appendText("\nYou can't do that.");
        }
    }
    
    @FXML
    private void attackFunction(ActionEvent event){
        if (game.currentRoom.getNPCList().contains(game.keyMonster)) {
            command.setSecondWord("monster");
            game.combat(command);
            if (playerInv.contains("rifle")) {
                command.setCommandWord(commandWord.USE);
                command.setSecondWord("rifle");
                game.combat(command);
                hpBarAction();
                String appendText;
            }
            else {
                textOutArea.appendText("\nNo rifle.");
            }
        }
        else if (!game.currentRoom.getNPCList().contains(game.keyMonster)) {
            textOutArea.appendText("\nNo monster here.");
        }
        else {
            textOutArea.appendText("\nYou can't do that.");
        }
    }
    
    private void hpBarAction(){
        double hpProgress = game.player.getCurrentHP() / 100.0;
        HPbar.setProgress(hpProgress);
    }
    private void AirBarAction(){
        double airProgress = game.player.getCurrentOxygen() / 100.0;
        AirBar.setProgress(airProgress);
    }
    @FXML
    private void statusButtonAction(){
        hpBarAction();
        AirBarAction();
    }
}