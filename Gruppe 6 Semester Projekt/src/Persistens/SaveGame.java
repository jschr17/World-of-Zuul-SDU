/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistens;

/**
 *
 * @author nickbsorensen
 */
import Acquaintance.IData;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;



public class SaveGame implements IData {

    public SaveGame() {
        JSONObject obj = new JSONObject();
        JSONArray test = new JSONArray();
        
    }

    @Override
    public boolean saveHighscore(ArrayList list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList loadHighscore() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
