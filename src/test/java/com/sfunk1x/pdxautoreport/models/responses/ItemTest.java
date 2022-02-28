package com.sfunk1x.pdxautoreport.models.responses;

import com.sfunk1x.pdxautoreport.models.Item;
import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

class ItemTest {

    private final BeanRunner beanRunner = new BeanRunner();

    @Test
    public void itemTest() throws Exception {
        beanRunner.testBean(new Item());
    }
}