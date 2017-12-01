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
import GlueCode.Starter;

/**
 *
 * @author Rasmus Jensen
 */
public class LogicFacade implements ILogic {
    static IData data;
    Highscore score;
    Game game;
    Command command;

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
        return score.getHighscoreString();
    }
    
    static public String getDescriptionText(String key){
        return data.getDescriptionText(key);
}

    @Override
    public String getItemDescription(String secondWord) {
        command.setSecondWord(secondWord);
        return game.getItemDescription(command);
    }

    @Override
    public String talk(String secondWord) {
        command.setSecondWord(secondWord);
        return game.talk(command);
    }

    @Override
    public String getHelpText() {
        return game.printHelp();
    }

    @Override
    public void getCurrentOxygen() {
        return game.player.getCurrentOxygen();
    }

    @Override
    public void getCurrentHP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String useItem(String secondWord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCurrentRoomName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPlayerName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeFromInventory(String secondWord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addInventory(String secondWord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getCurrentRoomInteractList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getCurrentRoomNPCList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goRoom(String secondWord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
