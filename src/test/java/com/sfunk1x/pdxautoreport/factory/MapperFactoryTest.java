package com.sfunk1x.pdxautoreport.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperFactoryTest {

    private static final MapperFactory mapperFactory = new MapperFactory();

    @Test
    void getJsonMapperTest() {
        ObjectMapper jsonMapper = mapperFactory.getJsonMapper();
        assertNotNull(jsonMapper);
    }

    @Test
    void getYamlMapperTest() {
        ObjectMapper yamlMapper = mapperFactory.getYamlMapper();
        assertNotNull(yamlMapper);
    }
}