/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistens;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick
 */
public class SaveGame {
    String loadfile;
    
    public SaveGame() {
    }
    
  public boolean saveGame(String saveString){
      PrintWriter save = null;
      boolean bool = false;
        try {
            save = new PrintWriter("files/SaveFile.txt");
            save.print(saveString);
            bool = true;
            } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            save.close();
        }
        return bool;
  }
                  
public String loadString(){
        try {
            String filePath = "files/SaveFile.txt";
            
            byte[] encoded = Files.readAllBytes(Paths.get(filePath));
            loadfile = new String(encoded, "utf-8");

        } catch (IOException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return loadfile;
}
    
          
    
    
}
