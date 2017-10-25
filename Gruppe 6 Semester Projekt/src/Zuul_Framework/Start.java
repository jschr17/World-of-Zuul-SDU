
package Zuul_Framework;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilde
 */
// the main Class of the game that initialises the Game() class and uses the play method on it
public class Start {
    public static void main(String[] args){
        try {       // we aare trying to catch the ioexeption trown from InputHasmap from persistens.
            Game game1 = new Game();
            game1.play();
        } catch (IOException e) {       // this is the catch where if an error from reading from the file shuld occur we want an errormessage printed
                System.err.println("Caught IOException: " + e.getMessage());
} 
    }
    
    
}
