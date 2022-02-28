package com.sfunk1x.pdxautoreport.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Item {

    @JsonProperty("address_input_value_x")
    private Double addressInputValueX;

    @JsonProperty("address_input_value_y")
    private Double addressInputValueY;

    @JsonProperty("date_created")
    private String dateCreated;

    @JsonProperty("text_input_value")
    private String textInputValue;

    @JsonProperty("date_updated")
    private String dateUpdated;

    @JsonProperty("files")
    private List<PdxReporterFile> pdxReporterFiles;

    @JsonProperty("status")
    private String status;

    @JsonProperty("status_input_value")
    private String statusInputValue;

    @JsonProperty("address_input_value_lon")
    private Double addressInputValueLon;

    @JsonProperty("category")
    private String category;

    @JsonProperty("address_input_value_lat")
    private Double addressInputValueLat;

    @JsonProperty("id")
    private int id;

    @JsonProperty("address_input_value_wmx")
    private Double addressInputValueWMX;

    @JsonProperty("address_input_value_wmy")
    private Double addressInputValueWMY;

    @JsonProperty("address_input_value")
    private String addressInputValue;

    public Double getAddressInputValueX() {
        return addressInputValueX;
    }

    public void setAddressInputValueX(Double addressInputValueX) {
        this.addressInputValueX = addressInputValueX;
    }

    public Double getAddressInputValueY() {
        return addressInputValueY;
    }

    public void setAddressInputValueY(Double addressInputValueY) {
        this.addressInputValueY = addressInputValueY;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTextInputValue() {
        return textInputValue;
    }

    public void setTextInputValue(String textInputValue) {
        this.textInputValue = textInputValue;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<PdxReporterFile> getFiles() {
        return pdxReporterFiles;
    }

    public void setFiles(List<PdxReporterFile> pdxReporterFiles) {
        this.pdxReporterFiles = pdxReporterFiles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusInputValue() {
        return statusInputValue;
    }

    public void setStatusInputValue(String statusInputValue) {
        this.statusInputValue = statusInputValue;
    }

    public Double getAddressInputValueLon() {
        return addressInputValueLon;
    }

    public void setAddressInputValueLon(Double addressInputValueLon) {
        this.addressInputValueLon = addressInputValueLon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAddressInputValueLat() {
        return addressInputValueLat;
    }

    public void setAddressInputValueLat(Double addressInputValueLat) {
        this.addressInputValueLat = addressInputValueLat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAddressInputValueWMX() {
        return addressInputValueWMX;
    }

    public void setAddressInputValueWMX(Double addressInputValueWMX) {
        this.addressInputValueWMX = addressInputValueWMX;
    }

    public Double getAddressInputValueWMY() {
        return addressInputValueWMY;
    }

    public void setAddressInputValueWMY(Double addressInputValueWMY) {
        this.addressInputValueWMY = addressInputValueWMY;
    }

    public String getAddressInputValue() {
        return addressInputValue;
    }

    public void setAddressInputValue(String addressInputValue) {
        this.addressInputValue = addressInputValue;
    }
}
