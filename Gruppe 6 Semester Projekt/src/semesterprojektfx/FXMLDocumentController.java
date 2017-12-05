package semesterprojektfx;

import Acquaintance.IImmovable;
import Acquaintance.ILogic;
import Acquaintance.INPC;
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
    GUIFacade gui;
    ILogic logic;
    INPC keyMonster;
    private GUIFacade scene = new GUIFacade();
    
//    GUIFacade starter = new GUIFacade();
    
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
    
    @FXML
    private ListView<String> highScoreList = new ListView<>();
    private ObservableList<String> highScoreView = FXCollections.observableArrayList();
    
    ListProperty<String> listProperty1 = new SimpleListProperty<>();
    ListProperty<String> listProperty2 = new SimpleListProperty<>();    
    ListProperty<String> listProperty3 = new SimpleListProperty<>();    
    @FXML
    private Button takeButton;
    @FXML
    private Button dropButton;
    @FXML 
    private Button talkButton;
    @FXML
    private Button saveButton;
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
    @FXML
    private Pane splashScreen;
    @FXML
    private Label enterNameLabel;
    @FXML
    private TextField playerNameEnterField;
    @FXML
    private Button startButton;
    @FXML
    private Label warningLabel;
    @FXML
    private Button highScoreButton;
    @FXML
    private Button loadButton;

    
    //This method controlls the functions of the player movement buttons, and the
    //help button.
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        String toAppend = "";
        roomInventory.getItems().clear(); 
        if (event.getSource() == northButton) {
            textOutArea.clear();
            String secondWord = "north"; // string
            toAppend = logic.goRoom(secondWord); // room description (hvad go room returner)
            roomChange();
        }
        else if (event.getSource() == eastButton) {
            textOutArea.clear();
            String secondWord = "east";
            toAppend = logic.goRoom(secondWord);
            roomChange();
        }
        else if (event.getSource() == westButton) {
            textOutArea.clear();
            String secondWord = "west";
            toAppend = logic.goRoom(secondWord);
            roomChange();
        }
        else if (event.getSource() == southButton) {
            textOutArea.clear();
            String secondWord = "south";
            toAppend = logic.goRoom(secondWord);
            roomChange();
        }
        else if (event.getSource() == helpButton){
            textOutArea.clear();
            toAppend = helpText();
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
            
            for (IImmovable i : logic.getCurrentRoomInteractList()){
                if (i.getItems() != null) {
                    roomInv.add(i.getItems().getName());   
                    roomInv.add(i.getName());   
                }
            }
            for(INPC n : logic.getCurrentRoomNPCList()){
                if (!logic.getCurrentRoomNPCList().isEmpty()){
                    roomInv.add(n.getName());
                }  
            }
            listProperty1.set(FXCollections.observableList(roomInv));
            roomInventory.itemsProperty().bind(listProperty1); 
        }
    }
    
    @FXML
    private void talkButtonAction(ActionEvent event) {
        String talkTarget = "";
        if (event.getSource() == talkButton) {
            talkTarget = roomInventory.getSelectionModel().getSelectedItem();
            textOutArea.appendText("\n" + talkText(talkTarget));
        }
    }
    
    /*
    *   This method deals with the inspect button.
    */
    @FXML
    private void inspectSelectionAction(ActionEvent event){
        String selectName = "selectName empty";
        if (event.getSource() == inspectButton) {
            // if, else if statements check which inventory the item is selected from.
            if (!roomInventory.getSelectionModel().isEmpty()){
                selectName = roomInventory.getSelectionModel().getSelectedItem();
            } else if (!playerInventory.getSelectionModel().isEmpty()) {
                selectName = playerInventory.getSelectionModel().getSelectedItem();
            }
            //textOutArea.clear();
            //System.out.println(inspectText(command));
            textOutArea.appendText("\n" + inspectText(selectName) + "."); // inspect
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
                else if (playerInv.size() < logic.getInventorySpace()) { // int som checker listens størelse...
                    if (!itemName.equalsIgnoreCase("monster") && !itemName.equalsIgnoreCase("counter") && !itemName.equalsIgnoreCase("device")) {
                        playerInv.add(itemName);
                        logic.addInventory(itemName);
                        roomInventory.getItems().remove(itemName);
                        textOutArea.appendText("\nYou have added " + itemName + " to your inventory.");
                        if (itemName.equalsIgnoreCase(medkit.getId())) {
                            medkit.setVisible(false);
                        }
                        else if (itemName.equalsIgnoreCase(oxygen.getId())) {
                            oxygen.setVisible(false);
                        }
                    }
                    else {
                        textOutArea.appendText("Can't take that.");
                    }

                }
                else if (playerInv.size() >= logic.getInventorySpace()) {
                    textOutArea.appendText("\nNo more space in your inventoy.");
                }
                
        }
        listProperty2.set(FXCollections.observableList(playerInv));
        playerInventory.itemsProperty().bind(listProperty2);
    }
    
    //Gets the help text string from the game class, so it can be used by the GUI
    private String helpText(){
        return logic.getHelpText();
    }
    

    private String inspectText(String secondWord){
        return logic.getItemDescription(secondWord);
    }
    
    private String talkText(String secondWord) {
        return logic.talk(secondWord);
    }
    
    //This method initializes all the relevant classes that are needed by the GUI
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        logic = gui.getInstance().getLogic();
        logic.loadHighscore();
        textOutArea.appendText("\n");
        textOutArea.appendText(logic.gameWelcome());
        textOutArea.appendText("\n");
        textOutArea.appendText("\n");                      
        
        hpBarAction();
        AirBarAction();
    } 

    @FXML
    private void dropButtonAction(ActionEvent event) {
        String itemName = "";
        if (playerInventory.getSelectionModel().getSelectedItem() != null) {
            itemName = playerInventory.getSelectionModel().getSelectedItem();
        }
        listProperty1.set(FXCollections.observableList(roomInv));
        roomInventory.itemsProperty().bind(listProperty1);
        if (itemName != "") {
            logic.removeFromInventory(itemName);
            playerInventory.getItems().remove(itemName);
            roomInv.add(itemName);
        }
        
        if (itemName.equalsIgnoreCase(medkit.getId())) {
            medkit.setVisible(true);
        }
        else if (itemName.equalsIgnoreCase(oxygen.getId())){
            oxygen.setVisible(true);
        }
    }
    
    private void roomChange() throws IOException{
        String roomName = logic.getCurrentRoomName();
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
            logic.useItem(newWord);
            textOutArea.appendText("\n" + logic.useItem(newWord)); // check om er på fasaden
            playerInventory.getItems().remove(newWord);
        }
        else {
            textOutArea.appendText("\nYou can't do that.");
        }
    }
    
    @FXML
    private void attackFunction(ActionEvent event){
        for (INPC npc : logic.getCurrentRoomNPCList()) {
            if (npc.getName().equalsIgnoreCase("monster")){
                keyMonster = npc;
            }      
        }
        boolean control = false;
        if (logic.getCurrentRoomNPCList().contains(keyMonster)) {
            logic.combat("monster");
            if (playerInv.contains("rifle") && control == false) {
                logic.useItem("rifle");
                //game.combat(command); does this need to be here?
                hpBarAction();
                textOutArea.appendText("\nYou attacked the monster with your rifle for 40 damage.");
                textOutArea.appendText("\n" + logic.combat("rifle") /*game.combat(command), don't know if this replacement works*/);
                if (logic.getDefeated() == true) {
                    monster.setVisible(false);
                }
                control = true;
                //return;
            }
            else {
                textOutArea.appendText("\nNo rifle.");
            }
        } 
        else if (!logic.getCurrentRoomNPCList().contains(keyMonster)) {
            textOutArea.appendText("\nNo monster here.");
        }
        else {
            textOutArea.appendText("\nYou can't do that.");
        }
    }
    private void hpBarAction(){
        double hpProgress = logic.getCurrentHP() / 100.0;
        HPbar.setProgress(hpProgress);
    }
    private void AirBarAction(){
        double airProgress = logic.getCurrentOxygen() / 100.0;
        AirBar.setProgress(airProgress);
    }
    @FXML
    private void statusButtonAction(){
        hpBarAction();
        AirBarAction();
    }
    @FXML
    private void saveGameAction(ActionEvent event) {
        //logic.saveGame();
    }
    
    @FXML
    private void splashScreenAction(ActionEvent event){
        String playerName = playerNameEnterField.getText();
        if (!playerName.equalsIgnoreCase("") && !playerName.equalsIgnoreCase(null)) {
            logic.setPlayerName(playerName);
            splashScreen.setVisible(false);
            medbay.setVisible(true);
            logic.setOxygen(100);
        }
        else {
            warningLabel.setVisible(true);
            warningLabel.setText("You need to input a name");
        }
    }

    @FXML
    private void highScoreLoad(ActionEvent event) {
        
        
        listProperty3.set(FXCollections.observableList(highScoreView));
        highScoreList.itemsProperty().bind(listProperty3);
    }
    
    @FXML   
    private void miniMapAction(){
        
    }
}
