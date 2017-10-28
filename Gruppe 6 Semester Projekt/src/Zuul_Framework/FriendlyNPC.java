package Zuul_Framework;

/**
 *
 * @author Wilde
 */
public class FriendlyNPC {
    private String name;
    private boolean hostile, movable;
    private Room currentRoom;
    
    public FriendlyNPC(String name, boolean hostile, boolean movable, Room room){
        this.name = name;
        this.hostile = hostile;
        this.movable = movable;
        this.currentRoom = room;
    }
    
    public String getName(){
        return this.name;
    }
    
    public boolean getHostility(){
        return this.hostile;
    }
    
    public void setHostility(boolean hostile){
        this.hostile = hostile;
    }
    /*
    public String getRoom(){
        return currentRoom.; //needs to extract string name of room instead of a room object
    }
    
    public void setRoom(Room room){
        this.currentRoom = room;
    }*/
}
