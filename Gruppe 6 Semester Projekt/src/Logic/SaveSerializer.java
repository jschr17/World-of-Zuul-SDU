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
        jg.writeNumberField("HP", t.getPlayer().getCurrentHP());
        jg.writeNumberField("AIR", t.getPlayer().getCurrentOxygen());
        jg.writeNumberField("AwesomePoint", t.getPlayer().getAwesomePoint(true));
        jg.writeBooleanField("CalledHelp", t.getPlayer().hasCalledHelp());
        jg.writeNumberField("TimePlayed", t.getPlayer().getTotalTimePlayed());
        jg.writeStringField("Inventory", t.getPlayer().getInventory().toString());
//        jg.writeObjectField("currentRoom", t.getGame().getCurrentRoom().getInteractList().toString());
//        jg.writeStringField("Roominventory", t.getGame().getCurrentRoom().getItemList().toString());
        //jg.writeStringField("medbay", t.getGame().getMedbay().getInteractList().toString());
        jg.writeStringField("medbay", t.getGame().getMedbay().toString()); //virker at skrive dem to string, men ikke som objecter
        jg.writeStringField("hallway", t.getGame().getHallway().toString());
        
        
        jg.writeEndObject();
    }
    
}
