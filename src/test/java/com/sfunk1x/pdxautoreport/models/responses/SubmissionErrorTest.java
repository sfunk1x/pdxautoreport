package com.sfunk1x.pdxautoreport.models.responses;

import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

public class SubmissionErrorTest {

    private final BeanRunner beanRunner = new BeanRunner();

    @Test
    public void submissionErrorTest() throws Exception {
        beanRunner.testBean(new SubmissionError());
    }
}
