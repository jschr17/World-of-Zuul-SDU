package Zuul_Framework;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
/**
 * this classe is of the enum data type:
 * enum  is a special data type that enables for a variable to be a set of predefined constants.
 * The variable must be equal to one of the values that have been predefined for it.
 * This class is used for declaring which words are considered a CommandWord
 */
public enum CommandWord
{   // declaration of enum constants which are assign a string value,
    //UNKNOWN is assigned (“?”) which relates that all other values, besides those assigned to another enum variable, is assigned to UNKNOWN
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?");
    
    private String commandString;   // all the above constants referees back to this variable
    
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
