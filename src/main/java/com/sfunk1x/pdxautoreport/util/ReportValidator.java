package com.sfunk1x.pdxautoreport.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sfunk1x.pdxautoreport.exceptions.InvalidReportException;
import com.sfunk1x.pdxautoreport.models.reports.Report;
import com.sfunk1x.pdxautoreport.models.reports.ReportList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.File;
import java.io.IOException;

@Component
public class ReportValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportValidator.class);
    private final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

    public void validateInputIsValidYaml(String yamlInputPath) {
        File yamlFile = new File(yamlInputPath);
        try {
            yamlMapper.readValue(yamlFile, ReportList.class);
            LOGGER.info("This is a valid report.");
        } catch (IOException ioException) {
            throw new InvalidReportException("Report file is not valid YAML. " + ioException.getMessage());
        }
    }

    public void validateReportList(ReportList reportList) {
        if (!StringUtils.hasText(reportList.getUsername()) ||
                !StringUtils.hasText(reportList.getPassword()) ||
                reportList.getReports().size() < 1 ) {
            throw new InvalidReportException("ReportList in invalid: " + reportList);
        }
        for (Report report : reportList.getReports()) {
            validateReport(report);
        }
    }

    void validateReport(Report report) {
        if (!StringUtils.hasText(report.getReportType()) ||
                !StringUtils.hasText(report.getLatitude()) ||
                !StringUtils.hasText(report.getLongitude()) ||
                report.getMessage() == null) {
            throw new InvalidReportException("Report is invalid: " + report);
        }
    }
}
