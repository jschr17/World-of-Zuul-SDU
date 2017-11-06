package Zuul_Framework;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
// this class is used for crating room objects for the player to transition between
public class Room {
    private String name, description;             //a string that will carry the description of the room
    private HashMap<String, Room> exits;    // HashMap that carrys each exit from the room and which room it leads to
    private HashMap<String, Room> secretExits;
    private ArrayList<Immovable> interactList; // list of the immovables in the room
    private ArrayList<Item> itemList;
    private ArrayList<NPC> npcList;             // list of NPCs in the room
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
        firstTimeEntered = false;
    }
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
    //This method returns everything in a given room that the player can interact with (Immovables)
    //and take with them(Items)
    public void searchRoom(){
        if(this.interactList.isEmpty() && this.npcList.isEmpty() && itemList.isEmpty()){
            System.out.println("There is nothing in the room");
        } else {
            System.out.println("You notice the following stuff in the room:");
            for(Immovable i : this.interactList){
                System.out.println(i.getName());
            }
            for(NPC n : this.npcList){
                System.out.println(n.getName());
            }
            for(Item i : this.itemList){
                System.out.println(i.getName());
            }
        }
    }

    //This method adds a specified immovable to the arraylist interactlist.
    public void setImmovables(Immovable immovables){
        this.interactList.add(immovables);
    }
    //This method returns an immovable as an object, when given the correct name.
    public Immovable getImmovable(String immovable) {
        Immovable object = null;
        for (Immovable i : this.interactList){
            if (i!=null &&i.getName().equals(immovable)) {
                object = i; 
            }
        
    }
        return object; //object might not have been initialised, but this method is not used if the object isn't found
    }
   
    public ArrayList<Immovable> getInteractList(){
        return interactList;
    }
    public ArrayList<Item> getItemList(){
        return itemList;
    }
    public void setItem(Item item){
        this.itemList.add(item);
    }
    public Item getItem(String item){
    Item object = null;
        for (Item i : this.itemList){
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
    public void removeItem(Item item){
        this.itemList.remove(item);
    }
    public NPC getNPC(String npc) {
        NPC object = null;
        for (NPC n : this.npcList){
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
    public void addItem(Item item){
        this.itemList.add(item);
    }
    
    public void removeNPC(NPC npc) {
        this.npcList.remove(npc);
    }
    public ArrayList<NPC> getNPCList(){
        return npcList;
    }
    Room getSecretDestination(String notes) {
        return secretExits.get(notes);
    }
    
    public Boolean getFirstTimeEntered() {
        return this.firstTimeEntered;
    }
    
    public void setFirstTimeEntered(Boolean entered) {
        this.firstTimeEntered = entered;
    }

}
