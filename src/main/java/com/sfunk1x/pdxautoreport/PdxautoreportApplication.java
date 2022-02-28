package com.sfunk1x.pdxautoreport;

import com.sfunk1x.pdxautoreport.util.PdxReporterHttpManager;
import com.sfunk1x.pdxautoreport.util.ReportValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class PdxautoreportApplication implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdxautoreportApplication.class);
    private static final String VALIDATE = "validate";
    private static final String SUBMIT = "submit";
    private static final String REPORTS_FILE = "reportsFile";
    private static final String HELP = "help";
    private static final String HELP_TEXT = "\npdxautoreport\n\n help - display this\nvalidate - validate whether a given file is formatted properly\nsubmit - submit a given set of reports to https://pdxreporter.org";
    private final PdxReporterHttpManager pdxReporterHttpManager;
    private final ReportValidator reportValidator;

    public PdxautoreportApplication(PdxReporterHttpManager pdxReporterHttpManager, ReportValidator reportValidator) {
        this.pdxReporterHttpManager = pdxReporterHttpManager;
        this.reportValidator = reportValidator;
    }

    public static void main(String[] args) {
		SpringApplication.run(PdxautoreportApplication.class, args);
	}

    @Override
    public void run(ApplicationArguments args) throws IOException {
        for (String nonOption : args.getNonOptionArgs()) {
            if (nonOption.equals(VALIDATE)) {
                LOGGER.info("Validating reports ...");
                reportValidator.validateInputIsValidYaml(args.getOptionValues(REPORTS_FILE).get(0));
            }
            if (nonOption.equals(SUBMIT)) {
                LOGGER.info("Submitting reports to https://pdxreporter.org ...");
                pdxReporterHttpManager.processReportList(
                        pdxReporterHttpManager.getReportList(
                                args.getOptionValues(REPORTS_FILE).get(0)));
            }
            if (nonOption.equals(HELP)) {
                LOGGER.info(HELP_TEXT);
            }
        }
    }
}
