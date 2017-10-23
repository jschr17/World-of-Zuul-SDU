/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul_Framework;                      // Its priamry package
                                             
import java.util.Arrays;                    //  Importing arrays
import java.util.Random;                   //   Importing random 
/**
 *
 * @author Muhaboy123
 */
public class keyMonster {     // Making a class named keyMonster(The monster that guards the key 
    
    // Objekter
    
    Random rand = new Random();   // objekt that makes a choosing randomly between numbers
    
    // Variiables
    
    
      String[] keyMonster = { "Predator" };       // Definening the monster as a string named "Predator"
      int health = 200;                          // 200 is the health of the monster          
      int keyMonsterAttackDamage = 50;          // 50 is damage that monster can damage
      int healthPackDropChance = 100;          // 100 procent of dropping a health pack when the monster is killed
      int numHealthPacks;                     // Number of health pack avaiable
    
    public void keyMonster() {                  // Here is the action of the monster is written

         int keyMonsterHealth = rand.nextInt(health);        // Setting health to randomly choose between numbers
        
    while(keyMonsterHealth > 0) {                    // loop that says whenever monsters health is bigger than 0 
        
       
        System.out.println("\t#" + Arrays.toString(keyMonster) + "'s HP: " + keyMonsterHealth);    // Print on the screen "monster has that amount of HP left" 
        
    }
    
    if (health < 1) {    // If monster has less than 1 health left 
        
        System.out.println( " # " + Arrays.toString(keyMonster) + " was defeated! " );   // Printinning out " that monster is defeated " 
        
    }
    if (rand.nextInt(100) < healthPackDropChance ) {   // Setting the chance of dropping the health pack to 100 procent 
    
       numHealthPacks++;          // adding a health pack
       System.out.println(" The " + Arrays.toString(keyMonster) + " dropped a health Pack! " ); // Monster have dropped a health pack
    
    }
    
    } 
    
       
    
    
}
