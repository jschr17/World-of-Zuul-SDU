/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import Logic.Command;
import Logic.CommandWord;
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
    public int getCurrentOxygen();
    public int getCurrentHP();
    public ArrayList getNPCList();
    public void setCommandWord(CommandWord command);
    public String combat(Command command);
    public boolean getDefeated();
}
