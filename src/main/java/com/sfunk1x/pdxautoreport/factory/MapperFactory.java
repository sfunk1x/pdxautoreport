package com.sfunk1x.pdxautoreport.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Component;

@Component
public class MapperFactory {

    public ObjectMapper getJsonMapper() {
        return new ObjectMapper();
    }

    public ObjectMapper getYamlMapper() {
        return new ObjectMapper(new YAMLFactory());
    }
}
