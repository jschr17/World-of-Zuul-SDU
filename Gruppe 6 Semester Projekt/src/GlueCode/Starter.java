/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GlueCode;

import Acquaintance.IData;
import Acquaintance.ILogic;
import Logic.Game;
import Logic.LogicFasade;
import Persistens.PersistenceFasade;
import java.io.IOException;

/**
 *
 * @author Rasmus
 */
public class Starter {
    
    public static void main(String[] args) {
        IData data = new PersistenceFasade();
        ILogic logic = new LogicFasade();
        logic.InjectData(data);
        logic.loadHighscore();
        System.out.println("Ready to launch");
       try {       // we are trying to catch the ioexeption trown from InputHasmap from persistens.
            Game game1 = new Game();
            game1.play();
        } catch (IOException e) {       // this is the catch where if an error from reading from the file shuld occur we want an errormessage printed
                System.err.println("Caught IOException: " + e.getMessage());    // the printline for the error messeage
}  
    }
    
    
}
