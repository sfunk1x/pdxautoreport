package com.sfunk1x.pdxautoreport;

import com.sfunk1x.pdxautoreport.models.reports.Message;
import com.sfunk1x.pdxautoreport.models.reports.Report;
import com.sfunk1x.pdxautoreport.models.reports.ReportList;
import com.sfunk1x.pdxautoreport.models.responses.ValidateUserResponse;
import com.sfunk1x.pdxautoreport.util.PdxReporterHttpManager;
import com.sfunk1x.pdxautoreport.util.ReportValidator;
import org.junit.jupiter.api.Test;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PdxautoreportApplicationTests {

    private static final String SAMPLE_REPORTS =
            "src/test/java/com/sfunk1x/pdxautoreport/resources/samplesReports/sampleReports.yaml";

    @Test
    public void http200_runTest() throws IOException {
        List<Report> listOfReports = new ArrayList<>() {{ add(constructTestReport()); }};
        ReportList reportList = new ReportList() {{
            setPassword("password");
            setUsername("username");
            setReports(listOfReports);
        }};
        ValidateUserResponse mockValidateUserResponse = mock(ValidateUserResponse.class);
        PdxReporterHttpManager mockPdxReporterHttpManager = mock(PdxReporterHttpManager.class);
        ReportValidator mockReportValidator = mock(ReportValidator.class);
        Response mockResponse = mock(Response.class);
        when(mockPdxReporterHttpManager.getReportList(anyString())).thenReturn(reportList);
        when(mockPdxReporterHttpManager.validateUser(anyString(), anyString())).thenReturn(mockResponse);
        when(mockPdxReporterHttpManager.getValidateUserResponse(any())).thenReturn(mockValidateUserResponse);
        String[] argsArray = new String[1];
        argsArray[0] = "validate --reportsFile=" + SAMPLE_REPORTS;
        PdxautoreportApplication.main(argsArray);
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
}
