/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul_Framework;

/**
 *
 * @author bruger
 */
//The item class.
//This is where the pickupable item objects is created.
public abstract class Item implements Interactables{
    //Name, description, and use description is defined.
    private String itemName, itemDescription, useDescription;
    private boolean flag;
    private final boolean isPickupable = true;
    
    //This is the method that created the item object.
    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }
    
    //The following methods are the methods implemented from our interactables
    //interface.
    @Override
    //returns the name of the object
    public String getName() {
        return itemName;
    }

    @Override
    //returns the description of the object
    public String getDescription() {
        return itemDescription;
    }

    @Override
    //returns the use description of the object
    public String getUseDescription() {
       return useDescription;
    }

    @Override
    public boolean isPickupable() {
       return this.isPickupable;
    }
    
    public void setFlag(boolean flag){
        this.flag = flag;
    }
}

