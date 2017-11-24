/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistens;
import java.io.IOException;
import java.util.ArrayList;
import Acquaintance.IData;

/**
 *
 * @author Rasmus Jensen
 */
public class PersistenceFasade implements IData{
    DataHighscore dataHs = new DataHighscore();
    InputHashmap textIn = new InputHashmap();
    
    
    
    @Override
    public boolean saveHighscore(ArrayList list){
        return dataHs.saveHigscore(list);
    }
    
    @Override
    public ArrayList loadHighscore(){
        return dataHs.loadHighscore();
        }
    
    @Override
    public String getDescriptionText(String key){
        return InputHashmap.getText(key);
    }
}
