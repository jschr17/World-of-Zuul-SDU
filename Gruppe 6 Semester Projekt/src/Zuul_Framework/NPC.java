package Zuul_Framework;

/**
 * @author Wilde
 */
public class NPC {
    private final String name;
    private boolean hostile, movable;
    private int health;
    
    /**
     *
     * @param name The name of the NPC, this is the name that will be written in commands to interact.
     * @param hostile The hostility of the NPC. true if hostile, false if not hostile. Can be set.
     * @param movable If the NPC can move from the room.
     */
    public NPC(String name, boolean hostile, boolean movable){
        this.name = name;
        this.hostile = hostile;
        this.movable = movable;
        this.health = 100;
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
    
    public boolean getMovability(){
        return this.movable;
    }
    /*
    public void attackNPC(){
        if (this.hostile){
            trigger battle_event;
        }
    }
    */
    /*public void moveNPC(Room newRoom){
        if (movable) {
            
        }
    }*/
}
