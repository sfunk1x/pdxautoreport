package com.sfunk1x.pdxautoreport.models.responses;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryResponseTest {

    private static final String CATEGORY_RESPONSE_JSON_FILE_PATH = "src/test/java/com/sfunk1x/pdxautoreport/resources/sampleResponses/categoryResponse.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BeanRunner beanRunner = new BeanRunner();

    @Test
    public void categoryResponseTest() throws Exception {
        beanRunner.testBean(new CategoryResponse());
    }

    @Test
    public void consumeCategoryResponseJsonTest() throws IOException {
        File categoryReponseJsonFile = new File(CATEGORY_RESPONSE_JSON_FILE_PATH);
        CategoryResponse categoryResponse = objectMapper.readValue(categoryReponseJsonFile, CategoryResponse.class);
        assertFalse(categoryResponse.getCategories().isEmpty());
    }
}
