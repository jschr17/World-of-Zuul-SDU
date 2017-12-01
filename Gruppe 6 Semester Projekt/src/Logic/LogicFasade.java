/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Acquaintance.IPersonalScore;
import java.io.IOException;
import java.util.ArrayList;
import Acquaintance.ILogic;
import Acquaintance.IData;

/**
 *
 * @author Rasmus Jensen
 */
public class LogicFasade implements ILogic {
    IData data;
    Highscore score;

    @Override
    public void InjectData(IData persistenceLayer) {
       this.data = persistenceLayer; 
    }
    
    public void loadHighscore(){
        score = new  Highscore(data.loadHighscore());
    }
       
    @Override
    public boolean saveHighscore(){
        return data.saveHighscore(Highscore.saveHighscore());
    }
    
    @Override
    public boolean addNewScore(String name, int score){
        return  this.score.addNewScore(name, score);
    }
    @Override
    public ArrayList getHighscore(){
        return score.getHighscore();
    }

    @Override
    public int getCurrentOxygen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCurrentHP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getNPCList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCommandWord(CommandWord command) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String combat(Command command) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getDefeated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}
