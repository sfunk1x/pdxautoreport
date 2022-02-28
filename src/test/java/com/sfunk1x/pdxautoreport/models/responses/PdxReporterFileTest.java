package com.sfunk1x.pdxautoreport.models.responses;

import com.sfunk1x.pdxautoreport.models.PdxReporterFile;
import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

class PdxReporterFileTest {

    private final BeanRunner beanRunner = new BeanRunner();

    @Test
    public void fileTest() throws Exception {
        beanRunner.testBean(new PdxReporterFile());
    }
}
