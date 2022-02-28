package com.sfunk1x.pdxautoreport.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidateUserRequest {

    @JsonProperty("action")
    private String action;

    @JsonProperty("api_url")
    private String apiUrl;

    @JsonProperty("contact_full")
    private String contactFull;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("version")
    private String version;

    public ValidateUserRequest() {}

    public ValidateUserRequest(String action, String apiUrl, String contactFull, String userName, String password, String version) {
        this.action = action;
        this.apiUrl = apiUrl;
        this.contactFull = contactFull;
        this.userName = userName;
        this.password = password;
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getContactFull() {
        return contactFull;
    }

    public void setContactFull(String contactFull) {
        this.contactFull = contactFull;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
