package Zuul_Framework;

import java.awt.Desktop;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
// the Class that contains the specifics in the game and assigns values to the initialized constructors
public class Game 
{
    private Parser parser;  //declares a parser objekt, so the game can read inputs
    private Room currentRoom;   // initialises a starting room
    private Player player = new Player(100, 100);
    //private Items items;
    //private Game game;
    // constructor for the game class    
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    //initializing of the rooms objects needed in the game 
    private void createRooms()
    {   //asigning the room objects
        Room medbay, keyRoom, armoury, hallway, communicationRoom, airlock;
        // The initialication of the room objects
        medbay = new Room("in a medical bay. A flickering light reveals "
                + "a counter, and a strange medical device in the corner.");
        
        keyRoom = new Room("in a dimly lit room. In the corner you see a large creature.");
        
        armoury = new Room("in an armoury, you see a weapon cabinet against the "
                + "eastern wall, a bookcase against the north wall, and a table in the middle of the room.");
        
        hallway = new Room("in a hallway, you see a large door to the east, "
                + "a window on the western wall, and a cabinet.");
        
        communicationRoom = new Room("in the communication room. A noisy radio "
                + "array is in the middle of the room. There is a panel next to "
                + "a large door.");
        
        airlock = new Room("in an airlock. There is an exit hatch in front of "
                + "you. On the eastern wall is a panel illuminated by a small "
                + "green LED,\n and on the western wall is a small glass cabinet.");

        //For testing!!!
        Item kettle, stick, sword;

        kettle = new Item("kettle","This is a fucking kettle");
        stick = new Item("stick","This is a fucking stick");
        sword = new Item("sword","This is a fucking sword");
        medbay.setItem(sword);
        medbay.setItem(kettle);
        medbay.setItem(stick);
        
        keyRoom.setItem(stick);
        
        armoury.setItem(kettle);
        armoury.setItem(sword);
        //For testing !!!
        
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
            currentRoom.searchRoom();
        }
        else if (commandWord == CommandWord.LOOK){

        }
        else if (commandWord == CommandWord.BREAK){
            breakObject(command);
        }
        else if (commandWord == CommandWord.CHECKBAG){
            System.out.println("You are carrying: " + player.getInventory());
        }
        else if (commandWord == CommandWord.TAKE){
            addInventory(command);
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
    private void getItemDescription(Command command) {
        if(!command.hasSecondWord()) {
            //Hvis der ikke er to ord, understående bliver printet og man
            //bliver bedt om at prøve igen.
            System.out.println("Which item?");
            return;
        }
        String item = command.getSecondWord();
        System.out.println(currentRoom.checkItems(item));
    }
    
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
    private void addInventory(Command command){
        String object = command.getSecondWord();
        if(!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        else{

            if (currentRoom.getItem(object) == null) {
                System.out.println("Can't take that!");
                return;
            }
            for(Object i : currentRoom.getItemList()){
            if(currentRoom.getItem(object).getName().equalsIgnoreCase(command.getSecondWord())){
                Item item = currentRoom.getItem(command.getSecondWord());

                    player.addToInventory(item.getName());
                    System.out.println("You have added " + item.getName() + " to your inventory.");
                    currentRoom.removeItem(item);
                    return;
                }
                
            }      
        }
    }    
}
