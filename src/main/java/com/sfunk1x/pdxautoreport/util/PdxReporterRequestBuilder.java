package com.sfunk1x.pdxautoreport.util;

import com.sfunk1x.pdxautoreport.models.reports.Report;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.stereotype.Component;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class PdxReporterRequestBuilder {

    private static final String API_URL_AUTH_STRING = "https://www.portlandmaps.com/api/auth/";
    private static final String API_URL_REPORT_STRING = "https://www.portlandmaps.com/api/report/";
    private static final Map<String, String> REPORT_ID = new HashMap<>() {{
       put("abandoned auto", "73");
       put("campsite reporting", "1790");
       put("debris in roadway", "66");
       put("graffiti", "77");
       put("illegal parking", "1324");
       put("other", "1459");
       put("park mintenance", "1206");
       put("plugged storm drain", "1261");
       put("potholes", "1489");
       put("sidewalk cafe violations", "1530");
       put("sidewalk vegetation", "314");
       put("street lighting", "596");
       put("work zone concerns", "1878");
    }};

    public FormDataMultiPart buildValidateUserBody(String userName, String password) {
        return new FormDataMultiPart()
                .field("action", "validateUser")
                .field("api_url", API_URL_AUTH_STRING)
                .field("user_name", userName)
                .field("password", password);
    }

    public FormDataMultiPart buildCategoriesRequestBody(String userName) {
        return new FormDataMultiPart()
                .field("action", "categories")
                .field("api_url", API_URL_REPORT_STRING)
                .field("user_name", userName);
    }

    public FormDataMultiPart buildItemsRequestBody(String userName) {
        return new FormDataMultiPart()
                .field("action", "items")
                .field("api_url", API_URL_REPORT_STRING)
                .field("user_name", userName);
    }

    public FormDataMultiPart buildReportToSubmit(String userName, Report report) throws IOException {
        String reportId = REPORT_ID.get(report.getReportType().toLowerCase(Locale.ROOT));
        FormDataMultiPart formDataMultiPart = (FormDataMultiPart) new FormDataMultiPart()
               .field("category_id", reportId)
               .field("input" + reportId + "_4_lat", report.getLatitude())
               .field("input" + reportId + "_4_long", report.getLongitude())
               .field("input" + reportId + "_3", report.getMessage().toString())
               .field("user_name", userName)
               .field("contact_type_id", "369")
               .field("action", "input")
               .field("api_url", API_URL_REPORT_STRING)
               .field("version", "v0.9.1")
               .bodyPart(getBodyPart(reportId, report.getReportPhoto()));
        formDataMultiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        return formDataMultiPart;
    }

    FormDataBodyPart getBodyPart(String reportId, String fileName) {
        File imageFile = new File(fileName);
        FormDataContentDisposition dispo = FormDataContentDisposition
                .name("input" + reportId + "_8")
                .fileName(imageFile.getName())
                .size(imageFile.length())
                .build();
        return new FormDataBodyPart(dispo, imageFile, MediaType.APPLICATION_OCTET_STREAM_TYPE);
    }
}
