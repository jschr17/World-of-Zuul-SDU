/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul_Framework;

/**
 *
 * @author SteamyBlizzard
 */
//This extension of interactableobjects defines a boolean value to be true, 
//to make sure that the player can pick the item up. Opposite from Immovables
public abstract class Pickups extends InteractablesObject{
    public boolean pickup;
    
    protected Pickups(){
        this.pickup = true;
    }
}
