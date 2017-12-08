package Logic;

/**
 * @author Wilde
 */

import Acquaintance.IItem;
import Acquaintance.INPC;
import java.util.ArrayList;

public class NPC implements INPC {
    private final String name;
    private String description;
    private boolean hostile, movable;
    private int health;
    private int baseDamage;
    private ArrayList<IItem> inventory;
    private Item item;
    private boolean toldToEvacuate;
    private boolean defeated;
    
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
        this.item = item;
        this.toldToEvacuate = false;
        this.defeated = false;
    }
    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String getDescription() {
        return this.description;
    }
    @Override
    public boolean getHostility(){
        return this.hostile;
    }
    @Override
    public void setHostility(boolean hostile){
        this.hostile = hostile;
    }
    
    @Override
    public boolean getMovability(){
        return this.movable;
    }
    @Override
    public void setDamage(int dmg) {
        this.baseDamage = dmg;
    }
    @Override
    public int getDamage() {
        return this.baseDamage;
    }
    @Override
    public void addItem(IItem item) {
        this.item = (Item) item;    // have to cast the IItem object into a Item object;
    }
    @Override
    public IItem getItem() {
        return item;
    }
    
    public void setHealth(int hp){
        this.health = hp;
    }
    @Override
    public int getHealth(){
        return this.health;
    }
    
    public void takeHit(int hit) {
        if (this.health > 0) {
            this.health -= hit;
        }
        if (this.health <= 0) {
            System.out.println(this.name + " has been killed.");
        }
    }

    public void setMovability(Boolean movable) {
        this.movable = movable;
    }
    
    public void setToldToEvacuate(Boolean evacuate) {
        this.toldToEvacuate = evacuate;
    }
    
    public Boolean toldToEvacuate() {
        return this.toldToEvacuate;
    }
    @Override
    public boolean getDefeated() {
        return this.defeated;
    }
    
    public void setDefeated(Boolean defeat) {
        this.defeated = defeat;
    }
}
