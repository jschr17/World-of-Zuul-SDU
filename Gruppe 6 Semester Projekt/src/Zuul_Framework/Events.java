/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul_Framework;

import java.util.HashMap;

/**
 *
 * @author bruger
 */
//Experiments with an events class, that handles all the events in the game
public class Events {
    //Initialization of room and interactobject classes.
    private Room room;
    private InteractablesObject interactables;
    
    //Initialization of a hashmap that will pair up objects and/or rooms that
    //interacts with eachother to do some sort of an event. This will make 
    //checking if you have the right item easier
    private HashMap<Room, InteractablesObject> eventPairs;
    
    //placeholder method. Not sure if i would like it to make objects or act as
    //the CommandWords class.
    public Events(){
        this.room = room;
        this.interactables = interactables;
    }
    
    //A method that checks the interactable if the flag is up (Det vi snakkede
    //om sammen med peter) and if it is it sets the exit in the direction you 
    //want.
    public void openHiddenPassage(InteractablesObject interactables, Room room, 
            Room targetRoom, String direction){
        if(interactables.getFlag()==true)
            room.setExit(direction, targetRoom);
        else
            System.out.println("Nothing happens...");       
    }
    //Another placeholder method. Dont look too deeply into this shit
    public void checkPrerequisite(){
      interactables.setFlag(true);
    }
    
    
}
