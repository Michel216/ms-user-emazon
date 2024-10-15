package com.emazon.user.adapters.driving.http.utils;

import com.emazon.user.adapters.driving.http.utils.exceptions.HttpJsonParserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonConversionException {

    private JsonConversionException(){
        throw new IllegalStateException("Utility class");
    }

    public static String toJson(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new HttpJsonParserException(e);
        }
    }
}
