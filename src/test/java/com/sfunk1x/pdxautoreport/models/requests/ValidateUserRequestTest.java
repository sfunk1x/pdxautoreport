package com.sfunk1x.pdxautoreport.models.requests;

import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ValidateUserRequestTest {

    private final BeanRunner beanRunner = new BeanRunner();

    @Test
    public void authEmptyConstructorTest() throws Exception {
        beanRunner.testBean(new ValidateUserRequest());
    }

    @Test
    public void authFullConstructorTest() {
        ValidateUserRequest validateUserRequest = new ValidateUserRequest("string", "string", "string", "string", "string", "string");
        assertNotNull(validateUserRequest);
    }
}