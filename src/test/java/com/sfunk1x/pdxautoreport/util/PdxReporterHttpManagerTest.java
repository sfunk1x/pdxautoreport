package com.sfunk1x.pdxautoreport.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfunk1x.pdxautoreport.client.PdxReporterHttpClient;
import com.sfunk1x.pdxautoreport.factory.MapperFactory;
import com.sfunk1x.pdxautoreport.models.Auth;
import com.sfunk1x.pdxautoreport.models.reports.Message;
import com.sfunk1x.pdxautoreport.models.reports.Report;
import com.sfunk1x.pdxautoreport.models.reports.ReportList;
import com.sfunk1x.pdxautoreport.models.responses.InputResponse;
import com.sfunk1x.pdxautoreport.models.responses.ValidateUserResponse;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PdxReporterHttpManagerTest {

    private static final PdxReporterRequestBuilder mockPdxReportRequestBuilder = mock(PdxReporterRequestBuilder.class);
    private static final PdxReporterHttpClient mockPdxReporterHttpClient = mock(PdxReporterHttpClient.class);
    private static final MapperFactory mockMapperFactory = mock(MapperFactory.class);
    private static final String inputResponseString = "{\"status\":\"success\",\"item_id\":2113295}";
    private static final ObjectMapper mockJsonMapper = mock(ObjectMapper.class);
    private static final ObjectMapper mockYamlMapper = mock(ObjectMapper.class);
    private static final ReportValidator mockReportValidator = mock(ReportValidator.class);
    private static PdxReporterHttpManager PDX_REPORTER_HTTP_MANAGER;
    private final Client mockClient = mock(Client.class);
    private final WebTarget mockWebTarget = mock(WebTarget.class);
    private final Invocation.Builder mockInvocationBuilder = mock(Invocation.Builder.class);
    private final Response mockResponse = mock(Response.class);
    private final Report mockReport = mock(Report.class);

    @BeforeAll
    public static void setup() {
        when(mockMapperFactory.getJsonMapper()).thenReturn(mockJsonMapper);
        when(mockMapperFactory.getYamlMapper()).thenReturn(mockYamlMapper);
        PDX_REPORTER_HTTP_MANAGER = new PdxReporterHttpManager(mockPdxReportRequestBuilder,
                mockPdxReporterHttpClient,
                mockMapperFactory,
                mockReportValidator);
    }

    @Test
    public void processListTest() throws IOException {
        ReportList reportList = constructReportList();
        ValidateUserResponse mockValidateUserResponse = mock(ValidateUserResponse.class);
        Response mockResponse = mock(Response.class);
        when(mockPdxReportRequestBuilder.buildReportToSubmit(anyString(), any()))
                .thenReturn(buildReportToSubmit());
        when(mockPdxReporterHttpClient.getClient()).thenReturn(mockClient);
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockClient.target(anyString()).request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).header(anyString(), anyString()))
                .thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).post(any())).thenReturn(mockResponse);
        when(mockPdxReportRequestBuilder.buildValidateUserBody(anyString(), anyString()))
                .thenReturn(new FormDataMultiPart());
        when(mockResponse.getStatus()).thenReturn(200);
        when(mockResponse.readEntity(String.class)).thenReturn("");
        when(mockJsonMapper.readValue(anyString(), eq(InputResponse.class)))
                .thenReturn(new InputResponse() {{ setItemId("123"); setStatus("success"); }});
        when(mockYamlMapper.readValue(anyString(), eq(ValidateUserResponse.class)))
                .thenReturn(mockValidateUserResponse);
        when(mockValidateUserResponse.isLoginValid()).thenReturn(true);
        PDX_REPORTER_HTTP_MANAGER.processReportList(reportList);
    }

    @Test
    public void processList_ReportSubmittedFailure_Test() throws IOException {
        ReportList reportList = constructReportList();
        ValidateUserResponse mockValidateUserResponse = mock(ValidateUserResponse.class);
        Response mockResponse = mock(Response.class);
        when(mockPdxReportRequestBuilder.buildReportToSubmit(anyString(), any()))
                .thenReturn(buildReportToSubmit());
        when(mockPdxReporterHttpClient.getClient()).thenReturn(mockClient);
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockClient.target(anyString()).request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).header(anyString(), anyString()))
                .thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).post(any())).thenReturn(mockResponse);
        when(mockPdxReportRequestBuilder.buildValidateUserBody(anyString(), anyString()))
                .thenReturn(new FormDataMultiPart());
        when(mockResponse.getStatus()).thenReturn(200);
        when(mockResponse.readEntity(String.class)).thenReturn("");
        when(mockJsonMapper.readValue(anyString(), eq(InputResponse.class)))
                .thenReturn(new InputResponse() {{ setItemId("123"); setStatus("failure"); }});
        when(mockYamlMapper.readValue(anyString(), eq(ValidateUserResponse.class)))
                .thenReturn(mockValidateUserResponse);
        when(mockValidateUserResponse.isLoginValid()).thenReturn(true);
        PDX_REPORTER_HTTP_MANAGER.processReportList(reportList);
    }

    @Test
    public void processList_NonHTTP200_Test() throws IOException {
        ReportList reportList = constructReportList();
        Response mockResponse = mock(Response.class);
        when(mockPdxReportRequestBuilder.buildReportToSubmit(anyString(), any()))
                .thenReturn(buildReportToSubmit());
        when(mockPdxReporterHttpClient.getClient()).thenReturn(mockClient);
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockClient.target(anyString()).request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).header(anyString(), anyString()))
                .thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).post(any())).thenReturn(mockResponse);
        when(mockPdxReportRequestBuilder.buildValidateUserBody(anyString(), anyString()))
                .thenReturn(new FormDataMultiPart());
        when(mockResponse.getStatus()).thenReturn(404);
        when(mockResponse.readEntity(String.class)).thenReturn("");
        PDX_REPORTER_HTTP_MANAGER.processReportList(reportList);
    }

    @Test
    public void processList_invalidLogin_Test() throws IOException {
        ReportList reportList = constructReportList();
        ValidateUserResponse mockValidateUserResponse = mock(ValidateUserResponse.class);
        Response mockResponse = mock(Response.class);
        when(mockPdxReportRequestBuilder.buildReportToSubmit(anyString(), any()))
                .thenReturn(buildReportToSubmit());
        when(mockPdxReporterHttpClient.getClient()).thenReturn(mockClient);
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockClient.target(anyString()).request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).header(anyString(), anyString()))
                .thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).post(any())).thenReturn(mockResponse);
        when(mockPdxReportRequestBuilder.buildValidateUserBody(anyString(), anyString()))
                .thenReturn(new FormDataMultiPart());
        when(mockResponse.getStatus()).thenReturn(200);
        when(mockResponse.readEntity(String.class)).thenReturn("");
        when(mockJsonMapper.readValue(inputResponseString, InputResponse.class))
                .thenReturn(new InputResponse() {{ setItemId("123"); setStatus("success"); }});
        when(mockYamlMapper.readValue(anyString(), eq(ValidateUserResponse.class)))
                .thenReturn(mockValidateUserResponse);
        when(mockValidateUserResponse.isLoginValid()).thenReturn(false);
        PDX_REPORTER_HTTP_MANAGER.processReportList(reportList);
    }

    @Test
    public void processList_ValidateUserResponseNull_Test() throws IOException {
        ReportList reportList = constructReportList();
        ValidateUserResponse mockValidateUserResponse = mock(ValidateUserResponse.class);
        Response mockResponse = mock(Response.class);
        when(mockPdxReportRequestBuilder.buildReportToSubmit(anyString(), any()))
                .thenReturn(buildReportToSubmit());
        when(mockPdxReporterHttpClient.getClient()).thenReturn(mockClient);
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockClient.target(anyString()).request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).header(anyString(), anyString()))
                .thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).post(any())).thenReturn(mockResponse);
        when(mockPdxReportRequestBuilder.buildValidateUserBody(anyString(), anyString()))
                .thenReturn(new FormDataMultiPart());
        when(mockResponse.getStatus()).thenReturn(200);
        when(mockResponse.readEntity(String.class)).thenReturn("");
        when(mockJsonMapper.readValue(inputResponseString, InputResponse.class))
                .thenReturn(new InputResponse() {{ setItemId("123"); setStatus("success"); }});
        when(mockYamlMapper.readValue(anyString(), eq(ValidateUserResponse.class)))
                .thenReturn(null);
        when(mockValidateUserResponse.isLoginValid()).thenReturn(false);
        PDX_REPORTER_HTTP_MANAGER.processReportList(reportList);
    }

    @Test
    public void validateUserTest() {
        when(mockPdxReportRequestBuilder.buildValidateUserBody(anyString(), anyString()))
                .thenReturn(buildValidateUserBody());
        when(mockPdxReporterHttpClient.getClient()).thenReturn(mockClient);
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockClient.target(anyString()).request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).post(any())).thenReturn(mockResponse);
        Response validateUserResponse =
                PDX_REPORTER_HTTP_MANAGER.validateUser("testUser", "testPassword");
        assertNotNull(validateUserResponse);
    }

    @Test
    public void getCategoriesTest() {
        when(mockPdxReportRequestBuilder.buildCategoriesRequestBody(anyString()))
                .thenReturn(buildCategoriesRequestBody());
        when(mockPdxReporterHttpClient.getClient()).thenReturn(mockClient);
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockClient.target(anyString()).request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).header(anyString(), anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).post(any())).thenReturn(mockResponse);
        Response categoryResponse = PDX_REPORTER_HTTP_MANAGER.getCategories("testUser", "testPogData");
        assertNotNull(categoryResponse);
    }

    @Test
    public void getItemsTest() {
        when(mockPdxReportRequestBuilder.buildItemsRequestBody(anyString()))
                .thenReturn(buildItemsRequestBody());
        when(mockPdxReporterHttpClient.getClient()).thenReturn(mockClient);
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockClient.target(anyString()).request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).header(anyString(), anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).post(any())).thenReturn(mockResponse);
        Response itemResponse = PDX_REPORTER_HTTP_MANAGER.getItems("sfunk1x", "pogUserData");
        assertNotNull(itemResponse);
    }

    @Test
    public void submitReportTest() throws IOException {
        when(mockPdxReportRequestBuilder.buildReportToSubmit(anyString(), any()))
                .thenReturn(buildReportToSubmit());
        when(mockPdxReporterHttpClient.getClient()).thenReturn(mockClient);
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockClient.target(anyString()).request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).header(anyString(), anyString()))
                .thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).post(any())).thenReturn(mockResponse);
        when(mockResponse.readEntity(String.class)).thenReturn(inputResponseString);
        when(mockJsonMapper.readValue(inputResponseString, InputResponse.class))
                .thenReturn(new InputResponse() {{ setItemId("123"); setStatus("success"); }});
        Auth auth = getAuth();
        InputResponse submitReportResponse =
                PDX_REPORTER_HTTP_MANAGER.submitReport(auth, mockReport);
        assertNotNull(submitReportResponse);
    }

    @Test
    public void submitReport_JsonProcessingExceptionTest() throws IOException {
        when(mockMapperFactory.getJsonMapper()).thenReturn(mockJsonMapper);
        when(mockPdxReportRequestBuilder.buildReportToSubmit(anyString(), any()))
                .thenReturn(buildReportToSubmit());
        when(mockPdxReporterHttpClient.getClient()).thenReturn(mockClient);
        when(mockClient.target(anyString())).thenReturn(mockWebTarget);
        when(mockClient.target(anyString()).request(anyString())).thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).header(anyString(), anyString()))
                .thenReturn(mockInvocationBuilder);
        when(mockClient.target(anyString()).request(anyString()).post(any())).thenReturn(mockResponse);
        when(mockResponse.readEntity(String.class)).thenReturn("mockEntityRead");
        when(mockJsonMapper.readValue("mockEntityRead", InputResponse.class))
                .thenThrow(JsonProcessingException.class);
        Auth auth = getAuth();
        InputResponse submitReportResponse =
                PDX_REPORTER_HTTP_MANAGER.submitReport(auth, mockReport);
        assertNull(submitReportResponse);
    }

    @Test
    public void getReportTest() throws IOException {
        ReportList reportListSource = new ReportList() {{ setPassword("test"); setUsername("test"); }};
        when(mockYamlMapper.readValue(any(File.class), eq(ReportList.class))).thenReturn(reportListSource);
        ReportList reportList = PDX_REPORTER_HTTP_MANAGER.getReportList("test");
        assertNotNull(reportList);
    }

    @Test
    public void getValidateUserResponseTest() throws IOException {
        Response mockResponse = mock(Response.class);
        when(mockResponse.readEntity(String.class)).thenReturn("");
        when(mockYamlMapper.readValue(anyString(), eq(ValidateUserResponse.class)))
                .thenReturn(new ValidateUserResponse());
        ValidateUserResponse validateUserResponse = PDX_REPORTER_HTTP_MANAGER.getValidateUserResponse(mockResponse);
        assertNotNull(validateUserResponse);
    }

    private FormDataMultiPart buildValidateUserBody() {
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        formDataMultiPart
                .field("action", "validateUser")
                .field("api_url", "https://www.portlandmaps.com/api/auth/")
                .field("user_name", "test")
                .field("password", "test");
        return formDataMultiPart;
    }

    private FormDataMultiPart buildCategoriesRequestBody() {
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        formDataMultiPart
                .field("action", "categories")
                .field("api_url", "https://www.portlandmaps.com/api/report/")
                .field("user_name", "test");
        return formDataMultiPart;
    }

    private FormDataMultiPart buildItemsRequestBody() {
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        formDataMultiPart
                .field("action", "items")
                .field("api_url", "https://www.portlandmaps.com/api/report/")
                .field("user_name", "test");
        return formDataMultiPart;
    }

    private FormDataMultiPart buildReportToSubmit() {
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        formDataMultiPart
                .field("action", "input")
                .field("api_url", "https://www.portlandmaps.com/api/report/")
                .field("user_name", "Test")
                .field("category_id", "75")
                .field("input73_4_lat", "45.47338483164245")
                .field("input73_4_long", "-122.59905338287355")
                .field("input73_3",
                        "some value")
                .field("contact_type_id", "369")
                .field("input73_8", "mockFileInputStream", MediaType.valueOf("image/jpeg"));
        return formDataMultiPart;
    }

    private Auth getAuth() {
        return new Auth() {{ setPassword("Test"); setUsername("test"); setPogUser("test"); }};
    }

    private Message constructTestMessage() {
        return new Message() {{
            setLegalName("legal name");
            setTelephoneNumber("1234567890");
            setClosestAddress("address");
            setVehicleColor("color");
            setVehicleBodyStyle("style");
            setVehicleDescription("description");
            setVehicleLicensePlate("plate");
            setVehicleMake("make");
        }};
    }

    private Report constructTestReport() {
        return new Report() {{
            setReportType("Abandoned Auto");
            setReportPhoto("filepath");
            setLatitude("latitude");
            setLongitude("longitude");
            setMessage(constructTestMessage());
        }};
    }

    private ReportList constructReportList() {
        List<Report> listOfReports = new ArrayList<>() {{ add(constructTestReport()); }};
        return new ReportList() {{
            setPassword("password");
            setUsername("username");
            setReports(listOfReports);
        }};
    }
}
