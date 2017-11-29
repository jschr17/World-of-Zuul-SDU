package Logic;

/** 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
// test
//test 2
public class Command
{   // a command object holds two strings,
    //one of the CoomandWord class and a second word
    private CommandWord commandWord;
    private String secondWord;
    //constructor
    public Command(CommandWord commandWord, String secondWord)
    {                                       
        this.commandWord = commandWord;
        this.secondWord = secondWord;
        //the class get initialized in the Parser class, which transfers a string value to each class attribute.
        //”this.” refers to that the attributes are set for each object of the class and not for the class in general.
    }
    // get method that allows other classes to receive commandWord 
    public CommandWord getCommandWord()
    {
        return commandWord;
    }
    // get method that allows other classes to receive secondWord
    public String getSecondWord()
    {
        return secondWord;
    }
    //Method for validating if a received commanWord corresponds to the commandwords defined in the CommandWord Class
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }
    //method that verifies if there is received a second word
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    public void setSecondWord(String newWord){
        this.secondWord = newWord;
    }
}

