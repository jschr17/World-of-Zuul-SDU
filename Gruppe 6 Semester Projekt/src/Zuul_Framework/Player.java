/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul_Framework;

import java.util.ArrayList;

/**
 *
 * @author Nick
 */
public class Player {
    private int hp, air;
    private ArrayList<String> inventory = new ArrayList<>();
    public Player(int newHP, int newAir) {
        this.hp = newHP;
        this.air = newAir;
    }
    public int getAir(){
    return air;
    }
    
   public void setAir(int newAir){
    air = newAir;
   }
   public int getHp(){
   return hp;
   }
    public void setHp(int newHp){
    hp = newHp;
    }
    public String getInventory(){
    return inventory.toString();
    }
    public void addToInventory(String newItem){
    inventory.add(newItem);
    
    }
    public void removeFromInventory(String newItem){
    inventory.remove(newItem);
    }
}
