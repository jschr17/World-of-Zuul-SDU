/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import java.io.IOException;

/**
 *
 * @author nickbsorensen
 */
//public class SaveDeserializer extends StdDeserializer<SaveFile> {
//    
//        
//    public SaveDeserializer(){
//    this(null);
//    }
//    
//    public SaveDeserializer(Class<?> vc){
//        super(vc);
//    
//    }
//    
//    
//    @Override
//    public SaveFile deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
//        JsonNode node = jp.getCodec().readTree(jp);
//        node
//        
//        Player player =
//        return player;
//    }
//    
//}
