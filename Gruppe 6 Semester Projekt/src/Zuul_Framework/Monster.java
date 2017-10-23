/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul_Framework;

import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author Muhaboy123
 */
public class Monster {  // Creating a class named monster
    
      //Objekter 
    
    Random rand = new Random();    // Creating a objekt that randomly reads numbers
    
    // Variiables
    
    
      String[] monster = { "Alien" };        // Setting name of the monster to Alien
      int health = 100;                     // Amount of health that monster has
      int monsterAttackDamage = 25;        // Amount of damaga that monster deals 
      int monsterSpawned = 50;      // The chance of monster will be in the room while player entered
    
    public void Monster() {                // Here is where all action is taken by the monster

         int monsterHealth = rand.nextInt(health);    // Setting health to randomly read numbers 
        Game GameObject = new Game(); 
        GameObject.createRooms();
    

    
        
    while(monsterHealth > 0) {        // If health reamaining is bigger than 0 
        
       
        System.out.println("\t#" + Arrays.toString(monster) + "'s HP: " + monsterHealth);  // Print out the remaining health of the monster
        
        
   // Player can choose bewtween attack and run
   // This function will come later in the player class
  

    }
    
    if (health < 1) {  // if the amount of health is under 1 
        
        System.out.println( " # " + Arrays.toString(monster) + " was defeated! " ); // Print out that monster is defeated 
        
    }
    
    
    }
    
    
    public void move() {

        Game GameObject = new Game(); 
        GameObject.createRooms();
        int MonsterSpawnedInRoom = rand.nextInt(monsterSpawned);
        
        
    }
     
     public static void room_encounter() {
  
        Game GameObject = new Game(); 
        GameObject.createRooms();
        
         Random gen = new Random();
  final int room_gen = gen.nextInt(3);
        switch (room_gen) {
            case1: sdsa
                break;
                casw
                    
                    
            
        }



  }

    
    
    
}
    
          
