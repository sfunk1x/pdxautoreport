package com.sfunk1x.pdxautoreport.models;


import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

class AuthTest {

    private final BeanRunner beanRunner = new BeanRunner();

    @Test
    public void authTest() throws Exception {
        beanRunner.testBean(new Auth());
    }
}