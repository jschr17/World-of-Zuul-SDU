package Zuul_Framework;

/**
 * @author Wilde
 */
public class NPC {
    private String name;
    private boolean hostile, movable;
    private int health;
    
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
    
    /*public void moveNPC(Room newRoom){
        if (movable) {
            
        }
    }*/
}
