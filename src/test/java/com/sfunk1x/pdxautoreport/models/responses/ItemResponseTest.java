package com.sfunk1x.pdxautoreport.models.responses;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ItemResponseTest {

    private static final String ITEM_RESPONSES_JSON_FILE_PATH = "src/test/java/com/sfunk1x/pdxautoreport/resources/sampleResponses/itemResponse.json";
    private final BeanRunner beanRunner = new BeanRunner();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void itemResponseTest() throws Exception {
        beanRunner.testBean(new ItemResponse());
    }

    @Test
    public void consumeSampleItemResponse() throws IOException {
        java.io.File itemResponsesJsonFile = new File(ITEM_RESPONSES_JSON_FILE_PATH);
        ItemResponse itemResponse = objectMapper.readValue(itemResponsesJsonFile, ItemResponse.class);
        assertNotNull(itemResponse);
    }
}