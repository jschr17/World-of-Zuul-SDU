/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistens;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 *
 * @author Rasmus
 */
public class ScoreSerializer extends StdSerializer<DataPS> {

    public ScoreSerializer() {
        this(null);
    }

    public ScoreSerializer(Class<DataPS> t) {
        super(t);
    }

    @Override
    public void serialize(DataPS t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeStartObject();
        jg.writeStringField("name", t.getName());
        jg.writeNumberField("score", t.getScore());
        jg.writeEndObject();
    }
}
