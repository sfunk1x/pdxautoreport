package com.sfunk1x.pdxautoreport.models.responses;

import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

class InputResponseTest {

    BeanRunner beanRunner = new BeanRunner();

    @Test
    public void inputResponseTest() throws Exception {
        beanRunner.testBean(new InputResponse());
    }
}
