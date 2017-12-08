package Logic;

import Acquaintance.IImmovable;
import Acquaintance.IItem;
import Acquaintance.INPC;
import Acquaintance.IRoom;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
// this class is used for crating room objects for the player to transition between
public class Room implements IRoom{
    private String name, description;             //a string that will carry the description of the room
    private HashMap<String, Room> exits;    // HashMap that carrys each exit from the room and which room it leads to
    private HashMap<String, Room> secretExits;
    private ArrayList<IImmovable> interactList; // list of the immovables in the room
    private ArrayList<IItem> itemList;
    private ArrayList<INPC> npcList;             // list of NPCs in the room
    private boolean firstTimeEntered;           
    
    
    //constructor that sets the rooms description
    public Room(String name, String description) 
    {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();    // a new exit HashMap is crated for each instance of room
        interactList = new ArrayList<>();
        itemList = new ArrayList<>();
        npcList = new ArrayList<>();
        secretExits = new HashMap<>();
        firstTimeEntered = true;
    }
    @Override
    public String getName(){
        return this.name;
    }
    
    //method for setting the exits of a room with a direction (key) and a neighbor room object
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    public void addSecretExit(String key, Room destination){
        secretExits.put(key, destination);
    }
    //method for returning a rooms description
    @Override
    public String getShortDescription()
    {
        return description;
    }
    
    // method for getting a rooms description and which exits it has.
    @Override
    public String getLongDescription()
    {
        return "\nYou are " + description + ".\n" + getExitString();
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
    //This method returns everything in a given room that the player can interact with (Immovables)
    //and take with them(Items)
    public void searchRoom(){
        if(this.interactList.isEmpty() && this.npcList.isEmpty() && itemList.isEmpty()){
            System.out.println("There is nothing in the room");
        } else {
            System.out.println("You notice the following stuff in the room:");
            for(IImmovable i : this.interactList){
                System.out.println(i.getName());
            }
            for(INPC n : this.npcList){
                System.out.println(n.getName());
            }
            for(IItem i : this.itemList){
                System.out.println(i.getName());
            }
        }
    }

    //This method adds a specified immovable to the arraylist interactlist.
    public void setImmovables(Immovable immovables){
        this.interactList.add((IImmovable)immovables);
    }
    //This method returns an immovable as an object, when given the correct name.
    @Override
    public IImmovable getImmovable(String immovable) {
        IImmovable object = null;
        for (IImmovable i : this.interactList){
            if (i!=null &&i.getName().equals(immovable)) {
                object = i; 
            }
        
    }
        return object; //object might not have been initialised, but this method is not used if the object isn't found
    }
   
    public ArrayList<IImmovable> getInteractList(){
        return interactList;
    }
    public ArrayList<IItem> getItemList(){
        return itemList;
    }
    public void setItem(IItem item){
        this.itemList.add(item);
    }
    public IItem getItem(String item){
    IItem object = null;
        for (IItem i : this.itemList){
            if (i.getName().equals(item)) {
                object = i;   
            } 
            if (object != null){
                return object;
            } else {
                break;
            }   
    }
        return object;
}
    @Override
    public void removeItem(IItem item){
        this.itemList.remove(item);
    }
    @Override
    public INPC getNPC(String npc) {
        INPC object = null;
        for (INPC n : this.npcList){
            if (n.getName().equals(npc)) {
                object = n;   
            } 
            /*if (object != null){
                return object;
            }*/ 
            else {
                break;
            }
        }
        return object; //object might not have been initialised, but this method is not used if the object isn't found
    }   
    public void addNPC(NPC npc){
        this.npcList.add(npc);
    }
    public void addItem(IItem item){
        this.itemList.add(item);
    }
    
    public void removeNPC(NPC npc) {
        this.npcList.remove(npc);
    }
    @Override
    public ArrayList<INPC> getNPCList(){
        return npcList;
    }
    public Room getSecretDestination(String notes) {
        return secretExits.get(notes);
    }
    
    public Boolean getFirstTimeEntered() {
        return this.firstTimeEntered;
    }
    
    public void setFirstTimeEntered(Boolean entered) {
        this.firstTimeEntered = entered;
    }

}
