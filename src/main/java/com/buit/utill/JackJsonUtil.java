package com.buit.utill;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JackJsonUtil {

    public static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
                jgen.writeString("");
            }
        });
    }

    public static String writeValueAsString(Object o){
        try {
            return mapper.writeValueAsString(o);
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T> T parse(String value,Class<T> clz){
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return mapper.readValue(value, clz);
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
