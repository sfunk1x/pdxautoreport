package com.sfunk1x.pdxautoreport.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sfunk1x.pdxautoreport.exceptions.InvalidReportException;
import com.sfunk1x.pdxautoreport.models.reports.Message;
import com.sfunk1x.pdxautoreport.models.reports.Report;
import com.sfunk1x.pdxautoreport.models.reports.ReportList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class ReportValidatorTest {

    private static final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
    private static final String sampleInvalidReportList =
            "src/test/java/com/sfunk1x/pdxautoreport/resources/samplesReports/invalidReportList.yaml";
    private static final String sampleReportList =
            "src/test/java/com/sfunk1x/pdxautoreport/resources/samplesReports/sampleReports.yaml";
    private static final String sampleReport =
            "src/test/java/com/sfunk1x/pdxautoreport/resources/samplesReports/sampleReport.yaml";
    private static final ReportValidator reportValidator = new ReportValidator();
    private final Report testReport = new Report();
    private final ReportList testReportList = new ReportList();

    @Test
    void validateReportListTest() throws IOException {
        ReportList validReportList = yamlMapper.readValue(new File(sampleReportList), ReportList.class);
        reportValidator.validateReportList(validReportList);
    }

    @Test
    void validateReportList_InvalidUsername_Test() {
        testReportList.setReports(new ArrayList<>() {{ add(testReport); }});
        testReportList.setPassword("password");
        Assertions.assertThrows(InvalidReportException.class, () -> reportValidator.validateReportList(testReportList));
    }

    @Test
    void validateReportList_InvalidPassword_Test() {
        testReportList.setReports(new ArrayList<>());
        testReportList.setUsername("username");
        Assertions.assertThrows(InvalidReportException.class, () -> reportValidator.validateReportList(testReportList));
    }

    @Test
    void validateReportList_InvalidReports_Test() {
        testReportList.setReports(new ArrayList<>());
        testReportList.setPassword("password");
        testReportList.setUsername("username");
        Assertions.assertThrows(InvalidReportException.class, () -> reportValidator.validateReportList(testReportList));

    }

    @Test
    void validateReportTest() throws IOException {
        Report validReport = yamlMapper.readValue(new File(sampleReport), Report.class);
        reportValidator.validateReport(validReport);
    }

    @Test
    void invalidReport_AllNull_Test() {
        Assertions.assertThrows(InvalidReportException.class, () -> reportValidator.validateReport(testReport));
    }

    @Test
    void invalidReport_ReportTypeNull_Test() {
        testReport.setLatitude("latitude");
        testReport.setLongitude("longitude");
        testReport.setMessage(new Message());
        testReport.setReportPhoto("photo");
        Assertions.assertThrows(InvalidReportException.class, () -> reportValidator.validateReport(testReport));
    }

    @Test
    void invalidReport_ReportLongitudeNull_Test() {
        testReport.setLatitude("latitude");
        testReport.setReportType("reportType");
        testReport.setMessage(new Message());
        testReport.setReportPhoto("photo");
        Assertions.assertThrows(InvalidReportException.class, () -> reportValidator.validateReport(testReport));
    }

    @Test
    void invalidReport_ReportLatitudeNull_Test() {
        testReport.setReportType("reportType");
        testReport.setLongitude("longitude");
        testReport.setMessage(new Message());
        testReport.setReportPhoto("photo");
        Assertions.assertThrows(InvalidReportException.class, () -> reportValidator.validateReport(testReport));
    }

    @Test
    void invalidReport_ReportMessageNull_Test() {
        testReport.setReportType("reportType");
        testReport.setLatitude("latitude");
        testReport.setLongitude("longitude");
        testReport.setMessage(null);
        testReport.setReportPhoto("photo");
        Assertions.assertThrows(InvalidReportException.class, () -> reportValidator.validateReport(testReport));
    }

    @Test
    void validateInputIsValidYaml_InvalidReportList_Test() {
        Assertions.assertThrows(InvalidReportException.class, () ->
                reportValidator.validateInputIsValidYaml(sampleInvalidReportList));
    }

    @Test
    void validateInputIsValidYaml_ValidReportList_Test() {
        reportValidator.validateInputIsValidYaml(sampleReportList);
    }
}
