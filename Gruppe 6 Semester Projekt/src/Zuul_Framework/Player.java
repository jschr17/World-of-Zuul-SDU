/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul_Framework;

import java.util.ArrayList;

/**
 *
 * @author Nick, Jonas
 */
public class Player {
    private int hp, air;
    private ArrayList<Item> inventory;
    
    public Player(int newHP, int newAir) {
        this.hp = newHP;
        this.air = newAir;
        inventory = new ArrayList<>();
    }
    //Return the players remaining air.
    public int getAir(){
    return air;
    }
    //Sets the players air. Adding to it or reducing it.
    public void setAir(int newAir){
    air = newAir;
    }
    //Returns the players HP.
    public int getHp(){
    return hp;
    }
    //Sets the players HP. Adding to it or reducing it.
    public void setHp(int newHp){
    hp = newHp;
    }
    public ArrayList<Item> getInventory(){
    return inventory;
    }
    public void addToInventory(Item newItem){
            inventory.add(newItem);          
    }
    public void removeFromInventory(Item newItem){
    inventory.remove(newItem);
    }
}
