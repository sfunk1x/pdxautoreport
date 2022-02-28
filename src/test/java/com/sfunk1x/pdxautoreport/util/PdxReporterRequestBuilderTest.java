package com.sfunk1x.pdxautoreport.util;

import com.sfunk1x.pdxautoreport.models.reports.Message;
import com.sfunk1x.pdxautoreport.models.reports.Report;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PdxReporterRequestBuilderTest {

    @Test
    public void buildValidateUserBodyTest() {
        PdxReporterRequestBuilder pdxReporterRequestBuilder = new PdxReporterRequestBuilder();
        FormDataMultiPart formDataMultiPart =
                pdxReporterRequestBuilder.buildValidateUserBody("username", "password");
        assertNotNull(formDataMultiPart);
    }

    @Test
    public void buildCategoriesRequestBodyTest() {
        PdxReporterRequestBuilder pdxReporterRequestBuilder = new PdxReporterRequestBuilder();
        FormDataMultiPart formDataMultiPart =
                pdxReporterRequestBuilder.buildCategoriesRequestBody("username");
        assertNotNull(formDataMultiPart);
    }

    @Test
    public void buildItemsRequestBodyTest() {
        PdxReporterRequestBuilder pdxReporterRequestBuilder = new PdxReporterRequestBuilder();
        FormDataMultiPart formDataMultiPart =
                pdxReporterRequestBuilder.buildItemsRequestBody("username");
        assertNotNull(formDataMultiPart);
    }

    @Test
    public void buildReportToSubmitTest() throws IOException {
        Report mockReport = mock(Report.class);
        when(mockReport.getReportType()).thenReturn("Abandoned Auto");
        when(mockReport.getLatitude()).thenReturn("latitude");
        when(mockReport.getLongitude()).thenReturn("longitude");
        when(mockReport.getMessage()).thenReturn(new Message());
        when(mockReport.getReportPhoto()).thenReturn("src/test/java/com/sfunk1x/pdxautoreport/resources/samplesReports/sampleReport.yaml");
        PdxReporterRequestBuilder pdxReporterRequestBuilder = new PdxReporterRequestBuilder();
        FormDataMultiPart formDataMultiPart =
                pdxReporterRequestBuilder.buildReportToSubmit("username", mockReport);
        assertNotNull(formDataMultiPart);
    }
}
