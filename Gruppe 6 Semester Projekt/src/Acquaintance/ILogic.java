/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import java.util.ArrayList;

/**
 *
 * @author Rasmus Jensen
 */
public interface ILogic {

    void InjectData(IData datalayer);
    public boolean addNewScore(String name, int score);
    public boolean saveHighscore();
    public ArrayList getHighscore();
    public void loadHighscore();
    public String getItemDescription(String secondWord);    //command.setSecondWord(secondWord)
    public String talk(String secondWord);                  //command.setSecondWord(secondWord)
    public String getHelpText();
    public void getCurrentOxygen();
    public void getCurrentHP();
    public String useItem(String secondWord);               //command.setSecondWord(secondWord)
    public String getCurrentRoomName();
    public void setPlayerName(String name);
    public void removeFromInventory(String secondWord);     //command.setSecondWord(secondWord)
    public void addInventory(String secondWord);            //command.setSecondWord(secondWord)
    public ArrayList getCurrentRoomInteractList();
    public ArrayList getCurrentRoomNPCList();
    public void goRoom(String secondWord);                  //command.setSecondWord(secondWord)
    public void saveGame();
    
}
