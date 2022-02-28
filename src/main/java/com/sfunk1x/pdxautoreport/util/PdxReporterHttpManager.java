package com.sfunk1x.pdxautoreport.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfunk1x.pdxautoreport.client.PdxReporterHttpClient;
import com.sfunk1x.pdxautoreport.factory.MapperFactory;
import com.sfunk1x.pdxautoreport.models.Auth;
import com.sfunk1x.pdxautoreport.models.reports.Report;
import com.sfunk1x.pdxautoreport.models.reports.ReportList;
import com.sfunk1x.pdxautoreport.models.responses.InputResponse;
import com.sfunk1x.pdxautoreport.models.responses.ValidateUserResponse;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class PdxReporterHttpManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdxReporterHttpManager.class);
    private static final String PDXREPORTER_API_VALIDATE_USER_URL = "https://pdxreporter.org/api.php";
    private static final String COOKIE_FRAGMENT = "pogUser-20171205=";
    private final ObjectMapper jsonMapper;
    private final ObjectMapper yamlMapper;
    private final PdxReporterRequestBuilder pdxReporterRequestBuilder;
    private final PdxReporterHttpClient client;
    private final ReportValidator reportValidator;

    @Autowired
    public PdxReporterHttpManager(PdxReporterRequestBuilder pdxReporterRequestBuilder,
                                  PdxReporterHttpClient client,
                                  MapperFactory mapperFactory,
                                  ReportValidator reportValidator) {
        this.pdxReporterRequestBuilder = pdxReporterRequestBuilder;
        this.client = client;
        this.jsonMapper = mapperFactory.getJsonMapper();
        this.yamlMapper = mapperFactory.getYamlMapper();
        this.reportValidator = reportValidator;
    }

    public void processReportList(ReportList reportList) throws IOException {
        reportValidator.validateReportList(reportList);
        Response response = validateUser(reportList.getUsername(), reportList.getPassword());
        ValidateUserResponse validateUserResponse = getValidateUserResponse(response);
        if (response.getStatus() == 200) {
            if (validateUserResponse != null && validateUserResponse.isLoginValid()) {
                Auth auth = new Auth() {{
                    setPassword(reportList.getPassword());
                    setUsername(reportList.getUsername());
                    setPogUser(validateUserResponse.toString());
                }};
                for (Report report : reportList.getReports()) {
                    InputResponse inputResponse = submitReport(auth, report);
                    if (inputResponse != null && inputResponse.getStatus().equals("success")) {
                        LOGGER.info("Report " + report.hashCode() + " submitted successfully.");
                    } else {
                        if (inputResponse != null && inputResponse.getSubmissionError() != null) {
                            LOGGER.error("Report " + report.hashCode() + " failed during submission: "+ inputResponse.getSubmissionError().getMessage());
                        } else {
                            LOGGER.error("Report " + report.hashCode() + " failed during submission.");
                        }
                    }
                }
            } else {
                LOGGER.error("Error logging into pdxreporter, exiting.");
            }
        } else {
            LOGGER.error("Error from pdxreporter.org: " + response.readEntity(String.class));
        }
    }

    public Response validateUser(String userName, String password) {
        FormDataMultiPart formDataMultiPart = pdxReporterRequestBuilder.buildValidateUserBody(userName, password);
        return client
                .getClient()
                .target(PDXREPORTER_API_VALIDATE_USER_URL)
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(formDataMultiPart, formDataMultiPart.getMediaType()));
    }

    public Response getCategories(String userName, String pogUser) {
        FormDataMultiPart formDataMultiPart = pdxReporterRequestBuilder.buildCategoriesRequestBody(userName);
        return client
                .getClient()
                .target(PDXREPORTER_API_VALIDATE_USER_URL)
                .request(MediaType.APPLICATION_XML)
                .header("Cookie", getCookieFragment(pogUser))
                .post(Entity.entity(formDataMultiPart, formDataMultiPart.getMediaType()));
    }

    public Response getItems(String userName, String pogUser) {
        FormDataMultiPart formDataMultiPart = pdxReporterRequestBuilder.buildItemsRequestBody(userName);
        return client
                .getClient()
                .target(PDXREPORTER_API_VALIDATE_USER_URL)
                .request(MediaType.APPLICATION_XML)
                .header("Cookie", getCookieFragment(pogUser))
                .post(Entity.entity(formDataMultiPart, formDataMultiPart.getMediaType()));
    }

    public InputResponse submitReport(Auth auth, Report report) throws IOException {
        InputResponse inputResponse = null;
        FormDataMultiPart formDataMultiPart =
                pdxReporterRequestBuilder.buildReportToSubmit(auth.getUsername(), report);
        LOGGER.info(formDataMultiPart.toString());
        Response response =  client
                .getClient()
                .register(MultiPartFeature.class)
                .target(PDXREPORTER_API_VALIDATE_USER_URL)
                .request(MediaType.APPLICATION_JSON)
                .header("Cookie", getCookieFragment(auth.getPogUser()))
                .post(Entity.entity(formDataMultiPart, formDataMultiPart.getMediaType()));
        try {
            inputResponse = jsonMapper.readValue(response.readEntity(String.class), InputResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return inputResponse;
    }

    public ReportList getReportList(String fileName) throws IOException {
        return yamlMapper.readValue(new File(fileName), ReportList.class);
    }

    public ValidateUserResponse getValidateUserResponse(Response response) throws JsonProcessingException {
        return yamlMapper.readValue(response.readEntity(String.class), ValidateUserResponse.class);
    }

    private String getCookieFragment(String pogUser) {
        return COOKIE_FRAGMENT + URLEncoder.encode(pogUser, StandardCharsets.UTF_8);
    }
}
