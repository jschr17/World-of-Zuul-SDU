package Zuul_Framework;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
//The job of this class is to take a input from the console and turn it into a commandWord and a secondWord
public class Parser 
{
    private CommandWords commands;  //Creates a commandword object
    private Scanner reader;         // Creates a Scanner objekt
    //Constructor that initializes the CommandWord and Scanner objekts
    public Parser() 
    {
        commands = new CommandWords();  
        reader = new Scanner(System.in);
    }
    //Method that takes two words from the scanner and turns them into command objects
    public Command getCommand() 
    {
        String inputLine;       //crates a variable to hold the input
        String word1 = null;    // variable to hold the first word
        String word2 = null;    // variable to hold the second word

        System.out.print("> "); // indicater to get the player to input a line

        inputLine = reader.nextLine();  //inputLine is equated to the Scanner input
        
        //StringTokenizer is a java utlity tool that breaks a String down into its individual words
        //here it is used on the InputLine variable to break it down into a word1 and a word2,
        //if any aditional words (more than 2) are in the Input.line they are ignored
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        return new Command(commands.getCommandWord(word1), word2);  //returnstatment that cast word1 into a CommandWord and word2 into a command 
    }
    // method for returning all Commands
    public String showCommands()
    {
        return commands.showAll();
    }
}
