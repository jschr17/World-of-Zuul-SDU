package Zuul_Framework;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
/**
 * this class is of the enum data type:
 * enum  is a special data type that enables for a variable to be a set of predefined constants.
 * The variable must be equal to one of the values that have been predefined for it.
 * This class is used for declaring which words are considered a CommandWord
 */
public enum CommandWord
{   // declaration of enum constants which are assign a string value,
    //UNKNOWN is assigned (“?”) which relates that all other values, besides those assigned to another enum variable, is assigned to UNKNOWN
    GO("go"),QUIT("quit"), HELP("help"), INSPECT("inspect"), UNKNOWN("?"),
    SEARCH("search"), USE("use"), TAKE("take"), DROP("drop"), 
    SHOOT("shoot"), LOOK("look"), BREAK("break"), CHECKBAG("check-bag"), 
    UNLOCK("unlock"),STATUS("status"),TAKEDMG("takedmg"),ACTIVATE("activate"), 
    FLEE("flee");
    
    private String commandString;   // all the above constants refers back to this variable
    
    //constructor 
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    //method that returns the string value of the given enum constant
    public String toString()
    {
        return commandString;
    }
}
