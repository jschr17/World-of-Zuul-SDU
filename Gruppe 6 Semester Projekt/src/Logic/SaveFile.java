/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Nick
 */
@JsonInclude(Include.NON_NULL)
public class SaveFile {

    Player player;
    
    private Game game;
    Room room;
    String Savestring;
    
    public SaveFile(Game game, Player player) {
        this.game = game;
        this.player = player;

    }
    public Game getGame(){
        return game;
    }
    
    public void setGame(Game game){
        this.game = game;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void SaveString() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        
        SimpleModule module = new SimpleModule();
        module.addSerializer(SaveFile.class, new SaveSerializer());
        mapper.registerModule(module);
        
        try {
//            Savestring = mapper.writeValueAsString(this);
            mapper.writeValue(new File("files/SaveFile.json"), this);
            
            System.out.println("test");
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SaveFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public String getSaveFile(){
        return Savestring;
    }
    
    
    public void LoadSaveString() throws IOException, JSONException{
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        
        //module.addDeserializer(SaveFile.class, new SaveDeserializer());
        mapper.registerModule(module);
        
        
        String filePath = "files/SaveFile.json";

        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        String testfile = new String(encoded, "utf-8");
        
        JSONObject json = new JSONObject(testfile);
        //Pick only the player part
        String playerPart = json.getJSONObject("player").toString();
        String medbayPart = json.getJSONObject("Medbay").toString();
        
        System.out.println("Stringen testfilen bliver printet: " + testfile);

        Player player1 = mapper.readValue(playerPart, Player.class);
        System.out.println("test 1");
        
        Room medbay1 = mapper.readValue(medbayPart, Room.class);
        System.out.println("Test 2: " + player1.toString());
        System.out.println("Test Room medbay: " + medbay1.toString());
        
        game.setPlayer(player1);
        game.setMedbay(medbay1);
    }
    
    
}
