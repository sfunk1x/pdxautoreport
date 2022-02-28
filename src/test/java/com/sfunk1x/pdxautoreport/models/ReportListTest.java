package com.sfunk1x.pdxautoreport.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sfunk1x.pdxautoreport.models.reports.ReportList;
import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportListTest {

    private static final String SAMPLE_REPORTS3_FILE_PATH
            = "src/test/java/com/sfunk1x/pdxautoreport/resources/samplesReports/sampleReports.yaml";
    private final BeanRunner beanRunner = new BeanRunner();
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @Test
    public void baseReportListTest() throws Exception {
        ReportList reportList = new ReportList();
        beanRunner.testBean(reportList);
    }

    @Test
    public void consumeReportListTest() throws IOException {
        ReportList reportList = objectMapper.readValue(new File(SAMPLE_REPORTS3_FILE_PATH), ReportList.class);
        assertTrue(reportList.getReports().size() > 0);
    }
}