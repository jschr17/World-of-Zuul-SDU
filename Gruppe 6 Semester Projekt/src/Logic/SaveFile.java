/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick
 */
public class SaveFile {

    Player player;
    private Game game;
    Room room;
    String Savestring;
    String testfile;
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
            
            System.out.println(Savestring);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SaveFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public String getSaveFile(){
        return Savestring;
    }
    
    public void LoadSaveString() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
//        module.addDeserializer(SaveFile.class, new SaveDeserializer());
        mapper.registerModule(module);
        
        
        
        String filePath = "files/SaveFile.json";
        
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        testfile = reader.readLine();
        
        //Player player = mapper.readValue(testfile, Player.class);
        //Player player = mapper.readValue(testfile, Player.class);
        //Player player2 = mapper.readValue(filePath, Player.class);
        //Player player3 = mapper.readValue("files/SaveFile", Player.class);
        Player player3 = mapper.readValue(new File("files/SaveFile.json"), Player.class);// io exception unrecognized token
        Player player4 = mapper.readValue(testfile, Player.class);
        game.setPlayer(player4);
        
        System.out.println(testfile);
        
    
    }
    
    
}
