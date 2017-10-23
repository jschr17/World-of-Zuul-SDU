
package Zuul_Framework;

import java.io.IOException;

/**
 *
 * @author Wilde
 */
// the main Class of the game that initialises the Game() class and uses the play method on it
public class Start {
    public static void main(String[] args) throws IOException { 
        Game game1 = new Game();
        game1.play();
    }
    
    
}
