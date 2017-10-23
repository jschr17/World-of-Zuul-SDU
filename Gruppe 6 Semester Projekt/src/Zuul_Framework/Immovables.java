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
//This extension of interactableobjects defines a boolean value to be false, 
//to prevent the player from picking it up. The opposite of the Pickups 
//extension
public abstract class Immovables extends InteractablesObject{
    public boolean pickup;
    
    protected Immovables(){
        this.pickup = false;
    }
}
