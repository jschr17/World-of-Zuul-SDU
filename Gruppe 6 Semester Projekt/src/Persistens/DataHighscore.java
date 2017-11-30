/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistens;

import Acquaintance.IPersonalScore;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rasmus Jensen
 */
public class DataHighscore {

    private ArrayList<IPersonalScore> scoreList = new ArrayList();

    public DataHighscore() {

    }

    boolean saveHigscore(ArrayList highscoreList){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ScoreSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(DataPS.class, new ScoreSerializer());
        mapper.registerModule(module);

        try {
            mapper.writeValue(new File("files/highscore.json"), highscoreList);
        } catch (IOException ex) {
            Logger.getLogger(DataHighscore.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    ArrayList loadHighscore() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ScoreSerializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(DataPS.class, new ScoreDeserializer());
        mapper.registerModule(module);


        try {
            scoreList = mapper.readValue(new File("files/highscore.json"), new TypeReference<ArrayList<DataPS>>(){});
            
        } catch (IOException ex) {
            Logger.getLogger(DataHighscore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scoreList;
    }

}
