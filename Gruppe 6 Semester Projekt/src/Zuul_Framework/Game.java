package Zuul_Framework;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
// the Class that contains the specifics in the game and assigns values to the initialized constructors
public class Game 
{
    private Parser parser;  //declares a paser objekt, so the game can read inputs
    private Room currentRoom;   // initialices a starting room
    // constructor for the game class    
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    //initializing of the rooms objekts needed in the game 
    private void createRooms()
    {   //asigning the room objects
        Room medbay, keyRoom, armoury, hallway, communicationRoom, airlock;
        // The initialication of the room objekts
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
        //the current room is assigned a room object
        currentRoom = medbay;
    }
    // the method that starts the game
    public void play() 
    {            
        printWelcome(); //prints the welcome message

        boolean finished = false; //initiates a boolen to determine if the game is finished
        while (! finished) {    // the main game loop, runs as long as boolean finnishd = false
            Command command = parser.getCommand(); // gets a command from the passer Class and processes it
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

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around an abandoned spaceship.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
