package Zuul_Framework;

/**
 * @author Wilde
 */

import java.util.ArrayList;

public class NPC {
    private final String name;
    private String description;
    private boolean hostile, movable;
    private int health;
    private int baseDamage;
    private ArrayList<Item> inventory;
    
    /**
     *
     * @param name The name of the NPC, this is the name that will be written in commands to interact.
     * @param description the description of the NPC given upon inspection.
     * @param hostile The hostility of the NPC. true if hostile, false if not hostile. Can be set.
     * @param movable If the NPC can move from the room.
     */
    public NPC(String name, String description, boolean hostile, boolean movable){
        this.name = name;
        this.description = description;
        this.hostile = hostile;
        this.movable = movable;
        this.health = 100;
        this.baseDamage = 0;
        inventory = new ArrayList<>();
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
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
    
    public void setDamage(int dmg) {
        this.baseDamage = dmg;
    }
    
    public int getDamage() {
        return this.baseDamage;
    }
    
    public void addItem(Item item) {
        inventory.add(item);
    }
    
    public ArrayList getItem() {
        return inventory;
    }
    
    public void takeHit(int hit) {
        if (this.health > 0) {
            this.health -= hit;
        }
        if (this.health <= 0) {
            System.out.println(this.name + " has been killed.");
        }
    }
}