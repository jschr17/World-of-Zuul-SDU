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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick
 */
public class SaveGame {
    
    public SaveGame() {
    }
    
  public void saveGame() throws IOException{
        BufferedWriter out = new BufferedWriter(new FileWriter("file/test.json"));
        out.write("Test");
  
  }
                  
    
    
          
    
    
}
