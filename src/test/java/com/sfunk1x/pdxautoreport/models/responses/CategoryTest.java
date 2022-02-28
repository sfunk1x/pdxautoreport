package com.sfunk1x.pdxautoreport.models.responses;

import com.sfunk1x.pdxautoreport.models.Category;
import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    private final BeanRunner beanRunner = new BeanRunner();

    @Test
    public void categoryTest() throws Exception {
        beanRunner.testBean(new Category());
    }
}
