package Zuul_Framework;
import Persistens.*;
import java.io.IOException;

import java.awt.Desktop;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
// the Class that contains the specifics in the game and assigns values to the initialized constructors
public class Game 
{
    InputHashmap text = new InputHashmap();
    private Parser parser;  //declares a parser objekt, so the game can read inputs
    private Room currentRoom;   // initialises a starting room
    private Player player = new Player(100, 100);
    private int inventorySpace = 2;
    private Immovable immovable;

    // constructor for the game class    
    public Game()throws IOException 
    {
        createRooms();
        parser = new Parser();
    }
    //initializing of the rooms objects needed in the game 
    private void createRooms()
    {   //asigning the room objects
        Room medbay, keyRoom, armoury, hallway, communicationRoom, airlock;
        // The initialication of the room objects

        medbay = new Room(text.getText("medbay"));
        keyRoom = new Room(text.getText("keyRoom"));
        armoury = new Room(text.getText("armory"));
        hallway = new Room(text.getText("hallway"));
        communicationRoom = new Room(text.getText("communicationRoom"));
        airlock = new Room(text.getText("airlock"));

        // assigning the room exits by using the exits HashMap to couple a sting "direction" with a room object
        medbay.setExit("north", keyRoom);
        
        keyRoom.setExit("south", medbay);
        keyRoom.setExit("east", armoury);
        keyRoom.setExit("north", hallway);

        //armoury.setExit("north", communicationRoom);  Can only be accessed after the secret passage is found
        armoury.setExit("west", keyRoom);

        //hallway.setExit("east", communicationRoom); Can only be accessed after getting quiz right, or unlocking from the inside
        hallway.setExit("north", airlock);
        hallway.setExit("south", keyRoom);
       
        //communicationRoom.setExit("west", hallway); Can only be accessed after unlocking the door (lever)
        communicationRoom.setExit("south", armoury);

        airlock.setExit("south", hallway);
        //creating immovables
        
        /* Creating and setting immovables for all the rooms */
        Immovable counter, device, table, weaponCabinet, bookcase, closet, glassCabinet, airlockPanel, doorLockPanel, radioArray;
        counter = new Immovable("counter", "A medical counter. There's a medkit on the countertop.", "You can't use this.", false, false);
        device = new Immovable("device", "A strange medical device. There's an oxygen tank attatched to it", "you don't know how to use this.", false, false);
        
        table = new Immovable("table", "A small table. There are a bunch of notes on top", "You can't use this.", true, false);
        weaponCabinet = new Immovable("cabinet","A weapon cabinet. There seems to be something inside","It's locked", false, true);
        bookcase = new Immovable("bookcase","A bookcase. There are no books left in it.","You move the bookcase to the side, and unveil hole in the wall.",false,false);
        
        closet = new Immovable("closet","A tall closet.","You open the closet, and a bunch of knives slide out. One of them hits your oxygen line.",false ,false);
        
        glassCabinet = new Immovable("cabinet","A glass cabinet. There is an oxygen tank inside.","You open the cabinet.",false ,false);
        airlockPanel = new Immovable("panel","A panel with a red light, and a large red button. ","You press the large button. The light turns green",false ,false); // needs to have added the death/rescue effect on use
        
        doorLockPanel = new Immovable("panel","A panel with a single lever on it. A label says \" door lock\" ","You pull the lever, and a loud clunk is heard.",false ,false);
        radioArray = new Immovable("radio","A radio array. Maybe you can use this to call for help.","Nothing happens, maybe the keyhole has something to do with it",false ,true);
                
        medbay.setImmovables(counter);
        medbay.setImmovables(device);
        
        armoury.setImmovables(table);
        armoury.setImmovables(weaponCabinet);
        armoury.setImmovables(bookcase);
        
        hallway.setImmovables(closet);
        
        airlock.setImmovables(glassCabinet);
        airlock.setImmovables(airlockPanel);
        
        communicationRoom.setImmovables(doorLockPanel);
        communicationRoom.setImmovables(radioArray);
        
        Item kettle, stick, sword, medkit, oxygen, gun, rifle, tableleg;

//      Items bliver "iniatileret"
        kettle = new Item("kettle","This is a fucking kettle",0,0,0);
        stick = new Item("stick","This is a fucking stick",0,0,0);
        sword = new Item("sword","This is a fucking sword",10,0,0);
        medkit = new Item("medkit","A medkit that can heal the user upon use",0,40,0);
        oxygen = new Item("oxygen-tank","An oxygen-tank, that can refill the users own oxygen-tank",0,0,35);
        gun = new Item("gun","A small gun, that can deal 20 dmg",20,0,0);
        rifle = new Item("rifle","A rifle, that has 40 dmg",40,0,0);
        tableleg = new Item("tableleg","A broken tableleg, from the table you just broke",1,0,0);
//      Items bliver sat i de forskellige immovables, i de forskellige rum:
//      Medbay items
        counter.setItems(medkit);
        device.setItems(oxygen); 
        
//      keyroom items:

//      Armoury items:
        weaponCabinet.setItems(rifle);
        
//      Hallway items:
        
//      Communicationroom items:
        
//      Airlock room items:
        glassCabinet.setItems(oxygen);

        //the current room is assigned a room object
        currentRoom = medbay;
    }
    
    // the method that starts the game
    public void play() 
    {            
        printWelcome(); //prints the welcome message

        boolean finished = false; //initiates a boolen to determine if the game is finished
        while (! finished) {    // the main game loop, runs as long as boolean finished = false
            Command command = parser.getCommand(); // gets a command from the parser Class and processes it
            finished = processCommand(command);     // after each command is prosed the came checks if the finish command have been given,
        }
        System.out.println("Thank you for playing.  Good bye."); //prints this line if finished == true
    }
    //Method that print the welcome + long description information when game is started
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Mads Effect!");
        System.out.println("Mads Effect is a new, incredibly exciting space-adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }
    // this boolean method is actualy what carries most of the ingame logic, and is the main component of the game loop
    // any new game commands must be assigned a opperator here!
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false; // here the want to quit boolean is initialized
        // a command object is created form the first word recognized from the Parser
        CommandWord commandWord = command.getCommandWord();
        //an if-statement for each of the enum objects defined in the CommandWord class
        // here there is designated the response the statements procure
        if(commandWord == CommandWord.UNKNOWN) {                // all strings that doesn't match any of the other enum statments
            System.out.println("I don't know what you mean..."); //returns this line in the console
            return false;
        }
        //if the input matches one other of the enum != UNKNOWN the response is determined there
        if (commandWord == CommandWord.HELP) {  // HELP results in caling the printHelp() method
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {   // GO is assigned the goRoom(command) method
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) { // QUIT assigneds the wantToQuit variable the quit(command) method
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.INSPECT){
            getItemDescription(command);
        }
        else if (commandWord == CommandWord.SEARCH) {
            search(command);
        }
        else if (commandWord == CommandWord.LOOK){
            //immovable.getDescription();
        }
        else if (commandWord == CommandWord.BREAK){
            breakObject(command);
        }
        else if (commandWord == CommandWord.CHECKBAG){
            if(player.getInventory().isEmpty()){
                System.out.println("You have nothing in your inventory");
            } else {
               System.out.println("You are carrying:");
                for(Item i : player.getInventory()){
                    if(i != null){
                        System.out.println(i.getName());
                    }                     
                } 
            }  
        }
        else if (commandWord == CommandWord.TAKE){
            addInventory(command);
        }
        else if (commandWord == CommandWord.DROP){
//            removeFromInventory(command);
        }
        else if(commandWord == CommandWord.STATUS){
        checkStatus(command);
        }
        else if(commandWord == CommandWord.USE){
            useItem(command);
        }
        else if (commandWord == CommandWord.TAKEDMG){
            takeDMG(command);
        }
            
        return wantToQuit; // the proccesCommand() method returns the want to quit boolean back to the play() method
    }
    // method for printing help, 
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around an abandoned spaceship.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    //method for moving between rooms
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {      //if statement for determining if there is a second word returned from the Parser
            System.out.println("Go where?");// if no word is given this line is printed in the console
            return;
        }

        String direction = command.getSecondWord(); //direction is sat to be the second word from the Parser

        Room nextRoom = currentRoom.getExit(direction);//initiates a new room object based on the Exit hashmap

        if (nextRoom == null) {                     //if no roomobject is found in the exit HashMap
            System.out.println("There is no door!");// this line is printed
        }
        else {
            currentRoom = nextRoom;                                // else the new room is sat to be currentRoom
            System.out.println(currentRoom.getLongDescription());   // and the long description is printed
        }
    }
    // the quit method that returns wantToQuit true if quit has ben sent from the Parser
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {           // if the Parser sends a second word along with quit
            System.out.println("Quit what?");   //this line is printed and wantToQuit returns false
            return false;
        }
        else {
            return true;
        }
    }
    //Returns the description of the word after the commandWord.
    private void getItemDescription(Command command) {
        if(!command.hasSecondWord()) {
            //Hvis der ikke er to ord, understående bliver printet og man
            //bliver bedt om at prøve igen.
            System.out.println("Which item?");
            return;
        }
        String item = command.getSecondWord();
        for(Item i : player.getInventory()){
             if(i.getName().equals(item)){
                 System.out.println(i.getDescription());
                 return;
             }
             else if(i.getName() != item){
                 continue;
             }
        }
        for(Immovable i : currentRoom.getInteractList()){
            if(i.getName().equals(item)){
                 System.out.println(i.getDescription());
                 return;
            }
            else if(i.getName() != item){
                continue;
            }
        }
        if(item != currentRoom.getInteractList().toString() && item != player.getInventory().toString()){
            System.out.println("You can't inspect that!");
        }
    }
    //Breaks the specified object by running the breakTable method
    private void breakObject(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("break what?");
            return;
        }
        String object = command.getSecondWord();
        if(currentRoom.getImmovable(object) != null)
            currentRoom.getImmovable(object).breakTable();
        else
            System.out.println("There is no " + object + " in this room");
        
    }
    //Adds the item comming after the commandWord to the players inventory.
    private void addInventory(Command command){
        String object = command.getSecondWord();
        if(!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        
        for(Immovable i : currentRoom.getInteractList()){
            if (player.getInventory().size() < inventorySpace) {
                if(i.getItems() != null && i.getItems().getName().equals(object)){
                System.out.println("You have added " + i.getItems().getName() + " to your inventory.");
                player.addToInventory(i.getItems());
                i.takeItem();
                return;         
                }
            }
        }
        for(Immovable i : currentRoom.getInteractList()){
        if(i.getItems() != null && player.getInventory().size() == inventorySpace && i.getItems().getName().equals(object)){
            System.out.println("No more space in the inventory!");
            return;
            }
        }
        System.out.println("There is no " + object + " here");  
    }
    private void search(Command command){
        if(!command.hasSecondWord()) {
            //If there is only one word, this will be printed and you will be
            //asked to try again.
            System.out.println("Search what?");
            return;
        }
        String searchTarget = command.getSecondWord();
        if(searchTarget.equals("room")){
            currentRoom.searchRoom();
        } else {
            for(Immovable i : currentRoom.getInteractList()){
                if(i.getItems()!=null && searchTarget.equals(i.getName())){
                    System.out.println(i.getItems().getName());
                    return;
                } 
            }
            System.out.println("You found nothing searching " + searchTarget);     
        }
    }
    /*
    private void removeFromInventory(Command command){
        String object = command.getSecondWord();
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
        }
        
        for(Item i : player.getInventory()){
            if (i.getName().equalsIgnoreCase(object)) {
                player.removeFromInventory(i);
                currentRoom.setItem(i);
                System.out.println("You put " + i.getName() + " on the floor.");
            }
        }
    }*/
    
    private void checkStatus(Command command){
        System.out.println("Your air tank is filled: " + player.getAir() + "% up");
        System.out.println("Your current HP is: " + player.getHp());
    
    }
    
    private void useItem(Command command){
        String object = command.getSecondWord();
        int air = player.getAir();
        int HP = player.getHp();
        String medkit = "medkit";
        String oxygen = "oxygen-tank";
        
        if(!command.hasSecondWord()) {
            System.out.println("Use what?");
            return;
        }
        for (Item i : player.getInventory()){
            if (i.getName().equalsIgnoreCase(object)){
                if (air < 65 && i.getName().equalsIgnoreCase(oxygen)){
                    System.out.println("You used the " + object);
                    player.setAir(air + i.getAir());
                    player.removeFromInventory(i);
                    return;
                }
                else if(HP < 60 && i.getName().equalsIgnoreCase(medkit)){
                    System.out.println("You used the " + object);
                    player.setHp(HP + i.getHP());
                    player.removeFromInventory(i);
                    return;
                }              
                else if(air > 65 && air != 100 || HP > 60 && HP != 100){
                    if(i.getName().equalsIgnoreCase(medkit)){
                        System.out.println("You used the: " + object);
                        player.setHp(100);
                        player.removeFromInventory(i);
                        return;
                    }
                    if(i.getName().equalsIgnoreCase(oxygen)){
                        System.out.println("You used the:: " + object);
                        player.setAir(100);
                        player.removeFromInventory(i);
                        return;
                    }
                }
                
                else if(air == 100){
                        System.out.println("Your oxygen-tank is already full");
                        return;
                    }
                    else if(HP == 100){
                        System.out.println("Your HP is already full");
                        return;
                    }
            }
        }
        // usikker på om jeg stadig har brug for denne failsafe
        for (Item i : player.getInventory()){
            if(!object.equalsIgnoreCase(i.getName())){
                System.out.println("Use what??");
                return;
            }
          
        }
        // usikker på om jeg har brug for denne failsafe, eller den lige over
            System.out.println("That item isnt in your inventory"); 
    }
    
    private void takeDMG(Command command){
        player.setHp(50);
        player.setAir(50);
    }
}