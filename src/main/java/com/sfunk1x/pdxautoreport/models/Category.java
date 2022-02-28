package com.sfunk1x.pdxautoreport.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {

    @JsonProperty("category_id")
    private int categoryId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("contact_required")
    private int contactRequired;

    @JsonProperty("text_input_id")
    private String textInputId;

    @JsonProperty("binary_input_id")
    private String binaryInputId;

    @JsonProperty("contact_type_id")
    private int contactTypeId;

    @JsonProperty("input_alias")
    private String inputAlias;

    @JsonProperty("binary_input_required")
    private int binaryInputRequired;

    @JsonProperty("address_input_required")
    private int addressInputRequired;

    @JsonProperty("text_input_required")
    private int textInputRequired;

    @JsonProperty("status_input_id")
    private String statusInputId;

    @JsonProperty("address_input_id")
    private String addressInputId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getContactRequired() {
        return contactRequired;
    }

    public void setContactRequired(int contactRequired) {
        this.contactRequired = contactRequired;
    }

    public String getTextInputId() {
        return textInputId;
    }

    public void setTextInputId(String textInputId) {
        this.textInputId = textInputId;
    }

    public String getBinaryInputId() {
        return binaryInputId;
    }

    public void setBinaryInputId(String binaryInputId) {
        this.binaryInputId = binaryInputId;
    }

    public int getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(int contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public String getInputAlias() {
        return inputAlias;
    }

    public void setInputAlias(String inputAlias) {
        this.inputAlias = inputAlias;
    }

    public int getBinaryInputRequired() {
        return binaryInputRequired;
    }

    public void setBinaryInputRequired(int binaryInputRequired) {
        this.binaryInputRequired = binaryInputRequired;
    }

    public int getAddressInputRequired() {
        return addressInputRequired;
    }

    public void setAddressInputRequired(int addressInputRequired) {
        this.addressInputRequired = addressInputRequired;
    }

    public int getTextInputRequired() {
        return textInputRequired;
    }

    public void setTextInputRequired(int textInputRequired) {
        this.textInputRequired = textInputRequired;
    }

    public String getStatusInputId() {
        return statusInputId;
    }

    public void setStatusInputId(String statusInputId) {
        this.statusInputId = statusInputId;
    }

    public String getAddressInputId() {
        return addressInputId;
    }

    public void setAddressInputId(String addressInputId) {
        this.addressInputId = addressInputId;
    }
}
