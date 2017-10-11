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
//An interface with the set methods our interactable objects use.
public interface Interactables {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getUseDescription();
    public abstract boolean isPickupable();
}
