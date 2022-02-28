package com.sfunk1x.pdxautoreport.models;

import com.sfunk1x.pdxautoreport.models.reports.Message;
import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;

class MessageTest {

    private final BeanRunner beanRunner = new BeanRunner();

    @Test
    public void baseReportMessageTest() throws Exception {
        Message message = new Message();
        beanRunner.testBean(message);
    }
}
