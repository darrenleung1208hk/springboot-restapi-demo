package io.darrenleung.springboot_restapi_demo.dtos;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.IOException;

public class BooleanDeserializer extends JsonDeserializer<Boolean> {
    
    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getCurrentToken().isBoolean()) {
            return p.getBooleanValue();
        }
        // Instead of throwing an exception, return null to let Bean Validation handle it
        return null;
    }
} 