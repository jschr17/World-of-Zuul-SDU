package semesterprojektfx;

import Zuul_Framework.*;
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
    private Command command;
    
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
//            System.out.println(game.currentRoom.getName());
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
            listProperty1.set(FXCollections.observableList(roomInv));
            roomInventory.itemsProperty().bind(listProperty1);  
            for (Immovable i : game.currentRoom.getInteractList()){
                if (i.getItems() != null) {
                    roomInv.add(i.getItems().getName());   
                    roomInv.add(i.getName());   
                }
            }
            for (NPC n : game.currentRoom.getNPCList()){
                if (!game.currentRoom.getNPCList().isEmpty()){
                    roomInv.add(n.getName());
                }  
            }
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
            command.setSecondWord(selectName);
            //System.out.println(inspectText(command));
            textOutArea.appendText("\n" + inspectText(command) + ".");
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
        textOutArea.setText("You clicked!");
    } 
    
    //Gets the help text string from the game class, so it can be used by the GUI
    private String helpText(){
        return game.printHelp();
    }
    
    private String inspectText(Command command){
        return game.getItemDescription(command);
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
        
        textOutArea.appendText(game.printWelcome());
        textOutArea.appendText("\n");
        textOutArea.appendText("\n");            
        textOutArea.appendText(game.currentRoom.getLongDescription());
        flag = 1;
    } 
    
    //Reconsider this method later!!!! Function is to print the welcome text when
    //the mouse enters the application area.
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
    private void dropButtonAction(ActionEvent event) {
        String itemName = "";
        if (playerInventory.getSelectionModel().getSelectedItem() != null) {
            itemName = playerInventory.getSelectionModel().getSelectedItem();
        }
        listProperty1.set(FXCollections.observableList(roomInv));
        roomInventory.itemsProperty().bind(listProperty1);
        if (itemName != null) {
            command.setSecondWord(itemName);
            game.removeFromInventory(command);
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
//        String _roomName = "FXML" + roomName + ".fxml";
//        System.out.println(_roomName);
//        if (!starter.getFXML().equalsIgnoreCase(_roomName)) {   
//            System.out.println("test1");
//            starter.setFXML(_roomName);
//            starter.start(stage);
//            System.out.println("test2");
//        }
//        System.out.println("Test 1");
//        if (game.currentRoom.getName().equalsIgnoreCase("keyRoom")) {
//            System.out.println("Test 2");
//            
//            Parent root2 = FXMLLoader.load(getClass().getResource("FXMLkeyRoom.fxml"));   
//            System.out.println("test 3");
//            Scene scene2 = new Scene(root2);
//            Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            newStage.setScene(scene2);
//            newStage.show();            
//            System.out.println("Test 4");
//        }
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
            
        }

    }
}