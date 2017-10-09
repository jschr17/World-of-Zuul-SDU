package Zuul_Framework;

import java.util.HashMap;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class CommandWords
{   //A HashMap is a list of parings of a key and its corresponding value,
    //when a key is sent to the HashMap it returns the corresponding value.
    private HashMap<String, CommandWord> validCommands;
    //method for entering the valid commands into a HashMap
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>(); //initialization of the HasMap
        for(CommandWord command : CommandWord.values()) {   //for-each loop that goes through the enum values of CommandWord
            if(command != CommandWord.UNKNOWN) {            //if the command isn't the UNKNOWN enuem it is added to the HashMap
                validCommands.put(command.toString(), command); // the command is the value and the string version of the command is set as the key
            }
        }
    }
    //Method that uses the HashMap vaildCommands to return the enuem command when its fed the coresponding string key
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    //A boolean method that checks if a string is a key for the validCommands HashMap
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }
    // method for printing all the commands in the validCommands HashMap
    public void showAll() 
    {
        for(String command : validCommands.keySet()) {  // for-each loop that prints each command value in the HashMap
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
