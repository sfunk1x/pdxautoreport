package com.sfunk1x.pdxautoreport.models.reports;

public class Report {

    private String reportType;
    private String reportingFrequency;
    private String latitude;
    private String longitude;
    private String reportPhoto;
    private Message message;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportingFrequency() {
        return reportingFrequency;
    }

    public void setReportingFrequency(String reportingFrequency) {
        this.reportingFrequency = reportingFrequency;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getReportPhoto() {
        return reportPhoto;
    }

    public void setReportPhoto(String reportPhoto) {
        this.reportPhoto = reportPhoto;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
