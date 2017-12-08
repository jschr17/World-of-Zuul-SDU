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
import Acquaintance.IImmovable;
import Acquaintance.IItem;
import Acquaintance.INPC;
import Acquaintance.IRoom;
import GlueCode.Starter;

/**
 *
 * @author Rasmus Jensen
 */
public class LogicFacade implements ILogic {

    static IData data;
    Highscore score;
    Game game;


    @Override
    public void InjectData(IData persistenceLayer) {
        this.data = persistenceLayer;
    }
    @Override
    public void InjectGame(Game game){  // the game is created in the starter class(gluecode) and is injected here so we can call methods on it.
        this.game = game;
    }

    public void loadHighscore() {
        score = new Highscore(data.loadHighscore());
    }

    @Override
    public boolean saveHighscore() {
        return data.saveHighscore(Highscore.saveHighscore());
    }

    @Override
    public boolean addNewScore(String name, int score) {
        return this.score.addNewScore(name, score);
    }

    @Override
    public ArrayList<String> getHighscore() {
        return score.getHighscoreString();
    }

    static public String getDescriptionText(String key) {
        return data.getDescriptionText(key);
    }

    @Override
    public String getItemDescription(String secondWord) {
         return game.getItemDescription(secondWord);
    }

    @Override
    public String talk(String secondWord) {
        return game.talk(secondWord);
    }

    @Override
    public String getHelpText() {
        return game.printHelp();
    }

    @Override
    public int getCurrentOxygen() {
        return game.player.getCurrentOxygen();
    }

    @Override
    public int getCurrentHP() {
        return game.player.getCurrentHP();
    }

    @Override
    public String useItem(String secondWord) {
        return game.useItem(secondWord);
    }

    @Override
    public String getCurrentRoomName() {
        return game.currentRoom.getName();
    }

    @Override
    public void setPlayerName(String name) {
        game.player.setPlayerName(name);
    }

    @Override
    public String removeFromInventory(String secondWord) {      // har endret returntype er muligvis ikke korrekt....
        return game.removeFromInventory(secondWord);
    }

    @Override
    public void addInventory(String secondWord) {
        game.addInventory(secondWord);
    }

    @Override
    public ArrayList<IImmovable> getCurrentRoomInteractList() {
        return game.currentRoom.getInteractList();
    }

    @Override
    public ArrayList<INPC> getCurrentRoomNPCList() {
        return game.currentRoom.getNPCList();
    }

    @Override 
    public ArrayList<IItem> getCurrentRoomItemList() {
        return game.currentRoom.getItemList();
    }

    @Override
    public String goRoom(String secondWord) {
        return game.goRoom(secondWord);
    }

    @Override
    public void saveGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String combat(String secondWord) {
        return game.combat(secondWord);
    }

    @Override
    public boolean getDefeated() {
        return game.keyMonster.getDefeated();
    }

    @Override
    public void setOxygen(int air) {
        game.player.setAir(air);
    }

    @Override
    public String gameWelcome() {
        return game.printWelcome();
    }
    @Override
    public int getInventorySpace() {
        return game.inventorySpace;
    }
//    @Override
//    public void Play(){
//        game.play();
//    }

    @Override
    public void monsterTravel() {
        game.monsterTravel();
    }

    @Override
    public ArrayList<INPC> getRoomNPCList(String roomName) {
        ArrayList<INPC> npcList = new ArrayList<>();
        for (IRoom r : game.getRoomList()){
            if (r.getName().equals(roomName)){
                npcList = r.getNPCList();
            }     
        }
        return npcList;
        
    }
    
    @Override
    public String awakenMonster() {
        return game.awakenMonster();
    }
    
    @Override
    public void setOpenSecretExit(String direction, String opener){
    game.currentRoom.setExit(direction, game.currentRoom.getSecretDestination(opener));
    }
    
    @Override
    public ArrayList<IItem> getPlayerInventory(){
        return game.player.getInventory();
    }

    @Override
    public String roomLogic() {
        return game.roomLogic();
    }

    @Override
    public INPC getCurrentRoomNPC(String npc) {
        return game.currentRoom.getNPC(npc);
    }

}
