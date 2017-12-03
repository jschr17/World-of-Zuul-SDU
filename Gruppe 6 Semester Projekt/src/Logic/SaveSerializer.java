/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 *
 * @author Nick
 */
public class SaveSerializer extends StdSerializer<SaveFile> {
    
    public SaveSerializer(){
    this(null);
    }
    
    public SaveSerializer(Class<SaveFile> t){
        super(t);
    
    }
    
    @Override
    public void serialize(SaveFile t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        jg.writeStartObject();
        jg.writeObjectField("player", t.getPlayer());
//        jg.writeObjectField("Medbay", t.getGame().getMedbay());
//        jg.writeObjectField("Hallway", t.getGame().getHallway());
//        jg.writeObjectField("Keyroom", t.getGame().getKeyRoom());
//        jg.writeObjectField("Communication", t.getGame().getCommunicationRoom());
//        jg.writeObjectField("Armoury", t.getGame().getArmoury());
//        jg.writeObjectField("Airlock", t.getGame().getAirlock());
//        jg.writeObjectField("CurrentRoom", t.getGame().getCurrentRoom());
       
        jg.writeEndObject();
    }
    
}
