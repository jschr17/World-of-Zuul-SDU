package Zuul_Framework;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
// this class is used for crating room objects for the player to transition between
public class Room 
{
    private String description;             //a sting that will carry the description of the room
    private HashMap<String, Room> exits;    // HashMap that carrys each exit from the room and which room it leads to
    //constructor that sets the rooms description
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();    // a new exit HashMap is crated for each instance of room
    }
    //method for setting the exits of a room with a direction (key) and a neighbor room object
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    //method for returning a rooms description
    public String getShortDescription()
    {
        return description;
    }
    // method for getting a rooms description and which exits it has.
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
        //private method that is used in here to descripe the exits based on the rooms exit HashMap 
	private String getExitString()
    {
        String returnString = "There are exits to the ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
        //method for getting the exits from a room
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

