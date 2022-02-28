package com.sfunk1x.pdxautoreport.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sfunk1x.pdxautoreport.models.reports.Report;
import net.sf.beanrunner.BeanRunner;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {

    private static final String SAMPLE_REPORT_FILE_PATH =
            "src/test/java/com/sfunk1x/pdxautoreport/resources/samplesReports/sampleReport.yaml";
    private final BeanRunner beanRunner = new BeanRunner();
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @Test
    public void reportTest() throws Exception {
        beanRunner.testBean(new Report());
    }

    @Test
    public void consumeSampleReportTest() throws IOException {
        Report report = objectMapper.readValue(new File(SAMPLE_REPORT_FILE_PATH), Report.class);
        assertEquals("reportType", report.getReportType());
        assertEquals("daily", report.getReportingFrequency());
        assertEquals("latitude", report.getLatitude());
        assertEquals("longitude", report.getLongitude());
        assertEquals("pathToImage.png", report.getReportPhoto());
        assertEquals("Legal Name", report.getMessage().getLegalName());
        assertEquals("123-456-7890", report.getMessage().getTelephoneNumber());
        assertEquals("closest address", report.getMessage().getClosestAddress());
        assertEquals("GM", report.getMessage().getVehicleMake());
        assertEquals("White", report.getMessage().getVehicleColor());
        assertEquals("Fullsize truck", report.getMessage().getVehicleBodyStyle());
        assertEquals("None", report.getMessage().getVehicleLicensePlate());
        assertEquals("Missing windshield, was towed into place", report.getMessage().getVehicleDescription());
    }
}
