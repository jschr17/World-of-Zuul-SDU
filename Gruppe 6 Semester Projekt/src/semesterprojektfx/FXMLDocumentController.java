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
    private SemesterProjektFX scene = new SemesterProjektFX();
    private Room room;
    
    private boolean flagcheck = false;
    
    SemesterProjektFX starter = new SemesterProjektFX();
    
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
    
    ListProperty<String> listPropertyRoom = new SimpleListProperty<>();
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
    private ImageView secretDoor1;
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
    private TextField password;
    private Pane passwordPane;
    @FXML
    private ImageView playerDot;
    @FXML
    private ImageView monsterDot;
    @FXML
    private ImageView britneyDot;
    @FXML
    private ImageView keyImg;
    @FXML
    private ImageView airlockMonster;
    @FXML
    private ImageView hallwayMonster;
    @FXML
    private ImageView armouryMonster;
    @FXML
    private ImageView medbayMonster;
    @FXML
    private ImageView keyRoomMonster;
    
    //This method controlls the functions of the player movement buttons, and the
    //help button.
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        String toAppend = "";
        game.monsterTravel(game.keyMonster);
        roomInventory.getItems().clear(); 
        if (event.getSource() == northButton) {
            textOutArea.clear();
            command.setSecondWord("north");          
            toAppend = game.goRoom(command);
            roomChange();
            minimapAction();
        }
        else if (event.getSource() == eastButton) {
            textOutArea.clear();
            command.setSecondWord("east");
            toAppend = game.goRoom(command);
            roomChange();
            minimapAction();
        }
        else if (event.getSource() == westButton) {
            textOutArea.clear();
            command.setSecondWord("west");
            toAppend = game.goRoom(command);
            roomChange();
            minimapAction();
        }
        else if (event.getSource() == southButton) {
            textOutArea.clear();
            command.setSecondWord("south");
            toAppend = game.goRoom(command);
            roomChange();
            minimapAction();
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
            for (Item i : game.currentRoom.getItemList()) {
                roomInv.add(i.getName());
            }
            for (Immovable i : game.currentRoom.getInteractList()){
                if(i.getFlag()==true){
                    roomInv.add(i.getName()); 
                    if (i.getItems() != null) {
                        if(i.getItems().getFlag()==true){
                            roomInv.add(i.getItems().getName());
                        }        
                    }
                }
            }
            for (NPC n : game.currentRoom.getNPCList()){
                if (!game.currentRoom.getNPCList().isEmpty()){
                    roomInv.add(n.getName());
                }  
            }
            listPropertyRoom.set(FXCollections.observableList(roomInv));
            roomInventory.itemsProperty().bind(listPropertyRoom); 
        }
    }
    
    @FXML
    private void talkButtonAction(ActionEvent event) {
        String talkTarget = "";
        if (event.getSource() == talkButton) {
            talkTarget = roomInventory.getSelectionModel().getSelectedItem();
            command.setSecondWord(talkTarget);
            textOutArea.appendText("\n" + talkText(command));
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
                    if (!itemName.equalsIgnoreCase("monster") && !itemName.equalsIgnoreCase("counter") && !itemName.equalsIgnoreCase("device") &&
                        !itemName.equalsIgnoreCase("closet") && !itemName.equalsIgnoreCase("table") && !itemName.equalsIgnoreCase("bookcase") 
                        && !itemName.equalsIgnoreCase("hiddenpanel") && !itemName.equalsIgnoreCase("lockedDoor") && !itemName.equalsIgnoreCase("airlockPanel") 
                        && !itemName.equalsIgnoreCase("doorLockPanel") && !itemName.equalsIgnoreCase("radioArray") && !itemName.equalsIgnoreCase("panel") 
                        && !itemName.equalsIgnoreCase("cabinet") && !itemName.equalsIgnoreCase("switch")) {
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
                        else if (itemName.equalsIgnoreCase("key")) {
                            keyImg.setVisible(false);
                        }
                        listProperty2.set(FXCollections.observableList(playerInv));
                        playerInventory.itemsProperty().bind(listProperty2);
                        return;
                    }
                    else {
                        textOutArea.appendText("\nCan't take that.");
                        return;
                    }
                }
                else if (playerInv.size() >= game.inventorySpace) {
                    textOutArea.appendText("\nNo more space in your inventoy.");
                }
//                else {
//                    textOutArea.appendText("\nCan't take that.");
//                }
                
        }
        listProperty2.set(FXCollections.observableList(playerInv));
        playerInventory.itemsProperty().bind(listProperty2);
    }
    
    //Gets the help text string from the game class, so it can be used by the GUI
    private String helpText(){
        return game.printHelp();
    }
    //Gets the item description of a specific item from the game class
    private String inspectText(Command command){
        return game.getItemDescription(command);
    }
    //Gets the different string responses from the game class, when you are 'talking'
    //whith Britney
    private String talkText(Command command) {
        return game.talk(command);
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
        
        hpBarAction();
        AirBarAction();
        
        splashScreen.setVisible(true);
        keyRoomMonster.setVisible(false);
        airlockMonster.setVisible(false);
        armouryMonster.setVisible(false);
        hallwayMonster.setVisible(false);
    } 
    //This method controls the function of the 'drop' button. It takes and sets
    //items in the two different lists.
    @FXML
    private void dropButtonAction(ActionEvent event) {
        String itemName = "";
        if (playerInventory.getSelectionModel().getSelectedItem() != null) {
            itemName = playerInventory.getSelectionModel().getSelectedItem();
        }
        if (itemName != "") {
            command.setSecondWord(itemName);
            game.removeFromInventory(command);
            playerInventory.getItems().remove(itemName);
            roomInv.add(itemName);
        }
        listPropertyRoom.set(FXCollections.observableList(roomInv));
        roomInventory.itemsProperty().bind(listPropertyRoom);
        
        if (itemName.equalsIgnoreCase(medkit.getId())) {
            medkit.setVisible(true);
        }
        else if (itemName.equalsIgnoreCase(oxygen.getId())){
            oxygen.setVisible(true);
        }
        else if (itemName.equalsIgnoreCase("rifle")){
            rifleImg.setVisible(true);
        }
    }
    //Controls which pane the GUI should show, based on where the player is.
    //It also runs the method 'awakenMonster', which makes the monster move from
    //room to room
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
            if (flagcheck == false) {
                game.awakenMonster();
                textOutArea.appendText("\n" + game.awakenMonster());
                flagcheck = true;
            }
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
    //Controls what happens when the player thies to use different items and
    //immovables (via the 'use' button). 
    @FXML
    private void useAction(ActionEvent event){
        String newWord = playerInventory.getSelectionModel().getSelectedItem();
        String newWord2 = roomInventory.getSelectionModel().getSelectedItem();
        if (!playerInv.isEmpty() && newWord != "rifle") {
            command.setSecondWord(newWord);
            textOutArea.appendText("\n" + game.useItem(command));
            game.useItem(command);  
            playerInventory.getItems().remove(newWord);
        } else if(newWord2=="panel"){
            textOutArea.appendText("\nYou go down to the small keypad"
                    + "\nPlease type in the password\n3 attemps remaining");
            passwordPane.setVisible(true);
           
        }
        else {
            textOutArea.appendText("\nYou can't do that.");
        }
    }
    private void submitPassword(ActionEvent event){
        int pass = 0;
        if(password.getText()!=null && !password.getText().isEmpty()){
            pass = Integer.parseInt(password.getText());
        } 
        if(pass!=28374){
            textOutArea.appendText("\nAccess Denied");
        } else if(pass==28374){
            textOutArea.appendText("\nAccess Granted");
            textOutArea.appendText("\nThe door slides open, leaving a opening to another room");
            secretDoor1.setVisible(true);
            game.currentRoom.setExit("north", game.currentRoom.getSecretDestination("notes"));
            passwordPane.setVisible(false);
        }
    }
    //
    @FXML
    private void attackFunction(ActionEvent event){
        boolean control = false;
        String newWord = roomInventory.getSelectionModel().getSelectedItem();
        if (game.currentRoom.getNPCList().contains(game.keyMonster) && newWord == "monster") {
            command.setSecondWord("monster");
            game.combat(command);
            if (playerInv.contains("rifle") && control == false) {
                command.setCommandWord(commandWord.USE);
                command.setSecondWord("rifle");
                game.combat(command);
                hpBarAction();
                textOutArea.appendText("\nYou attacked the monster with your rifle for 40 damage.");
                textOutArea.appendText("\n" + game.combat(command));
                if (game.keyMonster.getDefeated() == true) {
                    monster.setVisible(false);
                    roomInv.add("key");
                    keyImg.setVisible(true);
                }
                control = true;
                //return;
            }
            else {
                textOutArea.appendText("\nNo rifle.");
            }
        listPropertyRoom.set(FXCollections.observableList(roomInv));
        roomInventory.itemsProperty().bind(listPropertyRoom);
        }

        if(/*newWord.equalsIgnoreCase("table")*/ game.currentRoom.getInteractList().contains(game.table)){
            Immovable table = game.currentRoom.getImmovable("table");
            if (table.getDestructible() == true) {
            textOutArea.appendText("\nYou break the leg off the table \nA bunch of "
                    + "notes fall on the floor.");
            table.setDestructable(false); // since the immovable is broken, it can't be broken more.
            table.getItems().setFlag(true);
            roomInv.add(table.getItems().getName());
            return;
            } else if(table.getDestructible()==false) {
                textOutArea.appendText("\nThe table is destroyed");
                return;
            }
        }
        else if (!game.currentRoom.getNPCList().contains(game.keyMonster)) {
            textOutArea.appendText("\nNo monster here.");
        }
        else {
            textOutArea.appendText("\nYou can't do that.");
        }        
    }
    //Controls how the player HP bar functions
    private void hpBarAction(){
        double hpProgress = game.player.getCurrentHP() / 100.0;
        HPbar.setProgress(hpProgress);
    }
    //Controls how the players air bar functions
    private void AirBarAction(){
        double airProgress = game.player.getCurrentOxygen() / 100.0;
        AirBar.setProgress(airProgress);
    }
    //Updates the players airbar and HPbar
    @FXML
    private void statusButtonAction(){
        hpBarAction();
        AirBarAction();
    }
    //Controls how the splashscreen functions, and how it should respond if the 
    //player does something wrong
    @FXML
    private void splashScreenAction(ActionEvent event){
        String playerName = playerNameEnterField.getText();
        if (!playerName.equalsIgnoreCase("") && !playerName.equalsIgnoreCase(null)) {
            game.player.setPlayerName(playerName);
            splashScreen.setVisible(false);
            medbay.setVisible(true);   
            game.player.setAir(100);
        }
        else {
            warningLabel.setVisible(true);
            warningLabel.setText("You need to input a name");
        }
    }
    //NO FUNCTION YET !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! <----------------------------------------------------------------------------------------------
    @FXML
    private void highScoreLoad(ActionEvent event) {
        highScoreView.add("Jonas 123");
        highScoreView.add("Jonass 1234");
        highScoreView.add("Jonasss 1235");
        
        listProperty3.set(FXCollections.observableList(highScoreView));
        highScoreList.itemsProperty().bind(listProperty3);
    }
    //Displays and moves the different indicator on the games minimap, and controls
    //where and when the monster image must be displayed
    private void minimapAction() {
        String roomName = game.currentRoom.getName();
        
        if (game.keyMonster.getDefeated() == true) {
            game.monsterTravel(game.keyMonster);
        }
        if (roomName.equalsIgnoreCase("medbay")) {
            playerDot.setLayoutX(68);
            playerDot.setLayoutY(175);
        }
        else if (roomName.equalsIgnoreCase("keyRoom")) {
            playerDot.setLayoutX(78);
            playerDot.setLayoutY(129);
        }
        else if (roomName.equalsIgnoreCase("armoury")) {
            playerDot.setLayoutX(120);
            playerDot.setLayoutY(129);            
        }
        else if (roomName.equalsIgnoreCase("hallway")) {
            playerDot.setLayoutX(79);
            playerDot.setLayoutY(85);             
        }
        else if (roomName.equalsIgnoreCase("airlock")) {
            playerDot.setLayoutX(79);
            playerDot.setLayoutY(41);             
        }
        else if (roomName.equalsIgnoreCase("communicationRoom")) {
            playerDot.setLayoutX(105);
            playerDot.setLayoutY(86);             
        }  
        
        if (game.keyRoom.getNPCList().contains(game.keyMonster)) {
        monsterDot.setLayoutX(59);
        monsterDot.setLayoutY(129); 
            if (game.keyMonster.getDefeated() == true) {
                keyRoomMonster.setVisible(true);
            }
        
       }
        else if (game.armoury.getNPCList().contains(game.keyMonster)) {
        monsterDot.setLayoutX(105);
        monsterDot.setLayoutY(130);
        armouryMonster.setVisible(true);
        }   
        else if (game.hallway.getNPCList().contains(game.keyMonster)) {
        monsterDot.setLayoutX(59);
        monsterDot.setLayoutY(85);
        hallwayMonster.setVisible(true);
        }                
        else if (game.airlock.getNPCList().contains(game.keyMonster)) {
        monsterDot.setLayoutX(59);
        monsterDot.setLayoutY(40);
        airlockMonster.setVisible(true);
        } 
        if (!game.keyRoom.getNPCList().contains(game.keyMonster)) {
            keyRoomMonster.setVisible(false);
        }
        if (!game.armoury.getNPCList().contains(game.keyMonster)) {
            armouryMonster.setVisible(false);
        }        
        if (!game.hallway.getNPCList().contains(game.keyMonster)) {
            hallwayMonster.setVisible(false);
        }
        if (!game.airlock.getNPCList().contains(game.keyMonster)) {
            airlockMonster.setVisible(false);
        }
    }
}
