/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul_Framework;

/**
 *
 * @author Rasmus Jensen
 */
public class Interactable {
    
    private String description;
    private String activateDescription;
    
    public Interactable(String description, String activateDescription){
        this.description = description;
        this.activateDescription = activateDescription;
    }
    
    public String getDescription()
    {
        return description;
    }
    public String getActivateDescription(){
        return activateDescription;
    }
}
