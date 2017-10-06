package Zuul_Framework;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        Room medbay, keyRoom, armoury, hallway, communicationRoom, airlock;
      
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

        currentRoom = medbay;
    }

    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

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
