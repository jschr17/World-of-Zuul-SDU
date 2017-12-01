/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

/**
 *
 * @author Rasmus
 */
public interface IPersonalScore {
    
    /**
     *
     * @param name
     */
    void setName(String name);
    
    void setScore (int Score);
    
    String getName();
    
    int getScore();
    
    @Override
    String toString();
}
