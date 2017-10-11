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
public class Destructables extends Immovables {
    private String name, description, useDescription;
    
    public Destructables(String name, String description, 
            String useDescription){
        this.name = name;
        this.description = description;
        this.useDescription = useDescription;
    }
    public boolean isDestroyed(Boolean check){
        return check;
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public String getDescription(){
        return description;
    }
    @Override
    public String getUseDescription(){
        return useDescription;
    }
    @Override
    public boolean isPickupable(){
        return pickup;
    }
    
    
}
